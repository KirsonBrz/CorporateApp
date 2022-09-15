package com.kirson.corporateapp.core.routing.coordinator

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.kirson.corporateapp.core.routing.Route
import com.kirson.corporateapp.core.routing.RouteContext
import com.kirson.corporateapp.core.routing.transition.RouterTransitionType
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick

/**
 * Координатор, возвращающий результат при завершении сценария.
 * Если для начала сценария необходимы какие-то параметры, используйте [BaseFlowCoordinator1].
 */
abstract class BaseFlowCoordinator<Event, Result>(
  scope: Scope,
) : BaseFlowCoordinator1<Event, Unit, Result>(scope), FlowCoordinator<Event, Result> {

  final override fun start(beforePushClearUntil: Route?, onFlowFinish: ((Result) -> Unit)?) {
    return start(Unit, beforePushClearUntil, onFlowFinish)
  }

  final override fun openInitialRoute(
    params: Unit,
    beforePushClearUntil: Route?,
  ) {
    openInitialRoute(beforePushClearUntil)
  }
  abstract fun openInitialRoute(beforePushClearUntil: Route?)
}

/**
 * Координатор, принимающий на вход параметры и возвращающий результат при завершении сценария.
 * Если для начала сценария необходимы какие-то параметры, используйте [BaseFlowCoordinator1].
 */
abstract class BaseFlowCoordinator1<Event, Params, Result>(private val scope: Scope) :
  FlowCoordinator1<Event, Params, Result> {
  private var onFinish: ((Result) -> Unit)? = null
  protected val flowScopeName = scope.name as String
  private val disposable = CompositeDisposable()

  final override fun start(
    params: Params,
    beforePushClearUntil: Route?,
    onFlowFinish: ((Result) -> Unit)?,
  ) {
    logFlowStart(params)
    this.onFinish = onFlowFinish
    openInitialRoute(params, beforePushClearUntil)
  }

  final override fun finish(result: Result) {
    logFlowFinishing(result)
    Toothpick.closeScope(scope.name)
    disposable.dispose()
    onFinish?.invoke(result)
  }

  abstract fun openInitialRoute(
    params: Params,
    beforePushClearUntil: Route?,
  )
  abstract override fun handleEvent(event: Event)

  protected fun addDisposable(d: Disposable) {
    require(!disposable.isDisposed) { "trying to add disposable to finished coordinator" }
    disposable.add(d)
  }

  protected fun createContext(
    transitionType: RouterTransitionType = RouterTransitionType.Horizontal,
  ): RouteContext {
    return RouteContext(flowScopeName, transitionType)
  }

  private fun logFlowStart(params: Params) {
    Timber.d("┌─────────────────────────────────────────────────────────")
    Timber.d("│ Starting [$flowScopeName] flow")
    Timber.d("│  params=$params")
    Timber.d("└──────────────────────────────────────────────────────────")
  }

  private fun logFlowFinishing(result: Result) {
    Timber.d("┌─────────────────────────────────────────────────────────")
    Timber.d("│ Finishing [$flowScopeName] flow")
    Timber.d("│  result=$result")
    Timber.d("└──────────────────────────────────────────────────────────")
  }
}
