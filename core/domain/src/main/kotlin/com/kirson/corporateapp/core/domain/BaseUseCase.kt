package com.kirson.corporateapp.core.domain

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import com.kirson.corporateapp.core.domain.state.ConflatedStateStore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

abstract class BaseUseCase<S : Any>(
  viewModelScope: CoroutineScope,
  initialState: S,
) {
  private val stateStore = ConflatedStateStore(initialState, viewModelScope)

  protected val stateFlow: Flow<S>
    get() = stateStore.flow

  protected suspend fun setState(reducer: S.() -> S) {
    return suspendCoroutine { cont -> stateStore.set { reducer().also { cont.resume(Unit) } } }
  }

  protected suspend fun <T> withState(action: suspend (state: S) -> T): T {
    val state = suspendCoroutine<S> { cont -> stateStore.get { cont.resume(it) } }
    return action(state)
  }
}
