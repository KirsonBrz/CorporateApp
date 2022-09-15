package com.kirson.corporateapp.core.domain.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import java.util.concurrent.Executors

@ObsoleteCoroutinesApi
class ConflatedStateStore<S : Any>(initialState: S, scope: CoroutineScope) : StateStore<S> {
  private val setStateChannel = Channel<S.() -> S>(capacity = Channel.UNLIMITED)
  private val withStateChannel = Channel<suspend (S) -> Unit>(capacity = Channel.UNLIMITED)

  private val stateChannel = ConflatedBroadcastChannel(initialState)
  override val state: S
    get() = stateChannel.value

  override val flow: Flow<S>
    get() = stateChannel.asFlow()

  init {
    setupTriggerFlushQueues(scope)
    scope.coroutineContext[Job]!!.invokeOnCompletion {
      closeChannels()
    }
  }

  private fun setupTriggerFlushQueues(scope: CoroutineScope) {
    scope.launch(flushDispatcher) {
      try {
        while (isActive) {
          flushQueuesOnce()
        }
      } finally {
        closeChannels()
      }
    }
  }

  private fun closeChannels() {
    stateChannel.close()
    setStateChannel.close()
    withStateChannel.close()
  }

  private suspend fun flushQueuesOnce() {
    select<Unit> {
      setStateChannel.onReceive { reducer ->
        val newState = state.reducer()
        if (newState != state) stateChannel.send(newState)
      }
      withStateChannel.onReceive { block ->
        block(state)
      }
    }
  }

  override fun get(block: suspend (S) -> Unit) {
    withStateChannel.trySend(block)
  }

  override fun set(stateReducer: S.() -> S) {
    setStateChannel.trySend(stateReducer)
  }

  companion object {
    private val flushDispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
  }
}
