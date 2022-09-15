package com.kirson.corporateapp.core.routing.coordinator

import com.bluelinelabs.conductor.BuildConfig
import com.kirson.corporateapp.core.routing.Route
import com.kirson.corporateapp.core.routing.coordinator.entity.LifecycleCommand
import toothpick.Toothpick
import toothpick.config.Module

open class FlowConstructor<C : FlowCoordinator1<*, P, R>, P, R>(private val config: FlowConfig<C>) {
  val flowId = config.flowId

  @Suppress("FunctionName") // imitates constructor
  fun Builder(): FlowCoordinatorBuilder<C, P, R> {
    return FlowCoordinatorBuilder(config)
  }
}

data class FlowConfig<C>(
  val flowId: String,
  val flowModules: List<Module>,
  val coordinatorClass: Class<C>,
)

class FlowCoordinatorBuilder<C : FlowCoordinator1<*, P, R>, P, R>(private val config: FlowConfig<C>) {
  private var params: P? = null
  private var finishAction: ((R) -> Unit)? = null
  private var beforePushClearUntil: Route? = null

  fun withParams(params: P): FlowCoordinatorBuilder<C, P, R> {
    this.params = params
    return this
  }

  fun withFinishAction(action: (R) -> Unit): FlowCoordinatorBuilder<C, P, R> {
    this.finishAction = action
    return this
  }

  fun withClearUntil(beforePushClearUntil: Route): FlowCoordinatorBuilder<C, P, R> {
    this.beforePushClearUntil = beforePushClearUntil
    return this
  }

  fun start(parentScopeName: String) {
    val coordinator = createCoordinator(parentScopeName, config)
    start(coordinator)
  }

  fun buildLifecycleCommand(parentScopeName: String): LifecycleCommand<R> {
    val coordinator = createCoordinator(parentScopeName, config)
    return LifecycleCommand(
      start = { start(coordinator) },
      finish = { coordinator.finish(it) }
    )
  }

  private fun start(coordinator: C) {
    coordinator.start(
      params = params ?: error("params not specified when starting ${coordinator.javaClass.simpleName}"),
      beforePushClearUntil = this.beforePushClearUntil,
      onFlowFinish = finishAction ?: error("finishAction not specified")
    )
  }

  private fun createCoordinator(parentScopeName: String, config: FlowConfig<C>): C {
    val scope = Toothpick.openScopes(parentScopeName, config.flowId)
    // Игнорируется, т.к. Должен вызываться не очень часто
    @Suppress("SpreadOperator")
    scope.installModules(*config.flowModules.toTypedArray())
    if (BuildConfig.DEBUG) {
      checkCoordinatorIsBound(config.coordinatorClass)
    }
    return scope.getInstance(config.coordinatorClass)
  }

  private fun checkCoordinatorIsBound(clazz: Class<*>) {
    val isBound = config.flowModules.any { module -> module.bindingSet.any { it.key == clazz } }
    check(isBound) {
      "no coordinator DI-binding for ${clazz.canonicalName} found, check your flow modules"
    }
  }
}
