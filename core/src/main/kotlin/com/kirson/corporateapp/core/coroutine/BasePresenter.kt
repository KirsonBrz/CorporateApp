package com.kirson.corporateapp.core.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.rx2.asFlow
import kotlinx.coroutines.rx2.collect
import ru.dimsuz.unicorn.coroutines.Machine
import ru.dimsuz.unicorn.coroutines.MachineDsl
import ru.dimsuz.unicorn.coroutines.machine
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.MviView
import com.kirson.corporateapp.core.PresenterLifecycle
import com.kirson.corporateapp.core.UiIntentFactory
import com.kirson.corporateapp.core.UiIntentFactory0
import com.kirson.corporateapp.core.UiIntentFactory1
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<VS : Any, VI : BaseViewIntents, A : Any>(
  private val actionsContext: CoroutineContext = Dispatchers.Main.immediate,
) : PresenterLifecycle<VS, VI>, CoroutineScope by MainScopeImmediate() + CoroutineName("Presenter") {
  private var viewStateFlow = MutableSharedFlow<VS>(replay = 1)
  private var viewScope: CoroutineScope? = null
  private val intentBinders = arrayListOf<IntentBinder<VI>>()
  private var isFirstViewAttach = true
  protected var machine: Machine<VS, A>? = null
    private set

  protected abstract fun MachineDsl<VS, A>.buildMachine()

  private fun bindIntents() {
    val machine = machine<VS, A>(actionsContext) { buildMachine() }
    this.machine = machine

    machine.states
      .onEach { viewStateFlow.emit(it) }
      .catch { throw IllegalStateException("exception while reducing view state", it) }
      .launchIn(this)
  }

  override fun attachView(view: MviView<VS, VI>) {
    check(view.intents === view.intents) {
      "Expected View.intents to always return the same instance, internal error"
    }

    viewScope = MainScopeImmediate() + CoroutineName("View@${System.identityHashCode(view).toString(16)}")

    if (isFirstViewAttach) {
      isFirstViewAttach = false
      bindIntents()
    }

    intentBinders.forEach { binder ->
      viewScope?.launch {
        val intent = binder.intent(view.intents)
        view.intents.stream.asFlow()
          .filter { intent.isOwnerOf(it) }
          .catch { throw IllegalStateException("intent \"${intent.name}\" has thrown an exception", it) }
          .collect {
            binder.relay.emit(it.payload)
          }
      }
    }

    viewScope?.launch {
      viewStateFlow.collect { view.render(it) }
    }

    viewScope?.launch {
      view.intents.stream
        .collect { intent ->
          val screenName = this@BasePresenter.javaClass.name.takeLastWhile { it != '.' }.takeWhile { it != '$' }
          Timber.d("[$screenName, intent.name=${intent.name}]")
        }
    }
  }

  protected fun executeAsync(block: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.IO, block = block)

  override fun detachView() {
    viewScope?.cancel()
    viewScope = null
  }

  override fun destroy() {
    detachView()
    cancel()
    machine = null
    postDestroy()
  }

  protected open fun postDestroy() = Unit

  @Suppress("UNCHECKED_CAST") // internally type of payload is irrelevant
  private fun <I : Any> intentInternal(bindOp: (VI) -> UiIntentFactory): Flow<I> {
    val binder = IntentBinder(
      bindOp,
      MutableSharedFlow()
    )
    intentBinders.add(binder)
    return binder.relay as Flow<I>
  }

  @Suppress("UNCHECKED_CAST") // we actually know the type of payload
  @JvmName("intent1")
  fun <I : Any> intent(bindOp: (VI) -> UiIntentFactory1<I>): Flow<I> {
    return intentInternal(bindOp)
  }

  @Suppress("UNCHECKED_CAST") // we actually know the type of payload
  @JvmName("intent0")
  fun intent(bindOp: (VI) -> UiIntentFactory0): Flow<Unit> {
    return intentInternal(bindOp)
  }

  private data class IntentBinder<VI : Any>(
    val intent: (VI) -> UiIntentFactory,
    val relay: MutableSharedFlow<Any>,
  )
}
