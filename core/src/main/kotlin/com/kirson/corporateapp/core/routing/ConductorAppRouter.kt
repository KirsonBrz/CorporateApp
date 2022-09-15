package com.kirson.corporateapp.core.routing

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import com.bluelinelabs.conductor.BuildConfig
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.internal.LifecycleHandler
import com.kirson.corporateapp.core.annotations.RouterScreenFactory
import com.kirson.corporateapp.core.model.ScreenKey
import com.kirson.corporateapp.core.routing.transition.RouterOverlayType
import com.kirson.corporateapp.core.routing.transition.RouterTransitionType
import com.kirson.corporateapp.core.util.obtainTransaction
import com.kirson.corporateapp.core.util.toChangeHandler
import com.kirson.corporateapp.core.util.toTransitionType
import timber.log.Timber
import javax.inject.Inject

// Нет смысла разбивать на подклассы, т.к. это роутер, и для него нормально имплементировать столько функций
@Suppress("TooManyFunctions")
class ConductorAppRouter @Inject constructor(
  @RouterScreenFactory private val screenKeyFactory: (Route) -> ScreenKey,
) : Router {

  private lateinit var conductorRouter: com.bluelinelabs.conductor.Router
  private val routeObservers: MutableList<RouteObserver<Route>> = mutableListOf()

  /**
   * A mapping from controller instanceId to its route
   */
  private val controllerRoutes: MutableMap<String, Route> = mutableMapOf()

  override val backstack: List<Route>
    get() {
      return conductorRouter.backstack.map { transaction ->
        controllerRoutes[transaction.controller.instanceId]
          ?: error("internal error: controller ${transaction.controller} is not in the controllerRoutes")
      }
    }

  // (!) Must only be called by hosting activity
  fun attachTo(
    host: Activity,
    container: ViewGroup,
  ) {
    // Смотри NOTE_ROUTER_NOT_USING_ATTACH_ROUTER
    val lifecycleHandler = LifecycleHandler.install(host)
    // Смотри NOTE_ROUTER_AND_SAVED_INSTANCE_STATE
    conductorRouter = lifecycleHandler.getRouter(container, null).apply {
      addChangeListener(ChangeListener())
    }
  }

  // (!) Must only be called by hosting activity
  fun handleBack(): Boolean {
    return conductorRouter.handleBack()
  }

  // (!) Must only be called by hosting activity
  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    conductorRouter.onActivityResult(requestCode, resultCode, data)
  }

  override fun addRouteObserver(observer: RouteObserver<Route>) {
    routeObservers.add(observer)
  }

  override fun removeRouteObserver(observer: RouteObserver<Route>) {
    routeObservers.remove(observer)
  }

  override fun setRoot(route: Route, context: RouteContext) {
    return conductorRouter.setRoot(createTransaction(route, context).addToControllerRoutes(route))
  }

  override fun push(
    route: Route,
    context: RouteContext,
    beforePushClearUntil: Route?,
  ) {
    if (beforePushClearUntil == null) {
      pushInternal(route, context)
    } else {
      pushAndRemoveUntil(route, context, beforePushClearUntil)
    }
  }

  private fun pushInternal(route: Route, context: RouteContext) {
    route.toCommandSafe(conductorRouter.backstack) { routeType ->
      val transaction = createTransaction(routeType, context).addToControllerRoutes(routeType)
      conductorRouter.pushController(transaction)
    }
  }

  private fun pushAndRemoveUntil(
    route: Route,
    context: RouteContext,
    beforePushClearUntil: Route,
  ) {

    val transaction = createTransaction(route, context)
    val newBackstack = conductorRouter.backstack
      .dropLastWhile { beforePushClearUntil != controllerRoutes.getValue(it.controller.instanceId) }
      .plus(transaction)

    return route.toCommandSafe(newBackstack.dropLast(1)) {
      transaction.addToControllerRoutes(route)
      conductorRouter.setBackstack(newBackstack, transaction.pushChangeHandler())
      // see NOTE_ROUTE_MAPPING_CLEAN_UP
    }
  }

  override fun pop() {
    val poppedRootController = !conductorRouter.popCurrentController()
    if (poppedRootController) conductorRouter.activity?.finish()
    // see NOTE_ROUTE_MAPPING_CLEAN_UP
  }

  override fun popTo(route: Route) {
    val newBackstack = conductorRouter.backstack.dropLastWhile { it.tag() != route.path }
    if (newBackstack.isNotEmpty()) {
      // use pop transition from controller we're popping, otherwise would've to use some arbitrary one
      // which would look incorrect
      val popTransition = conductorRouter.backstack.last().popChangeHandler()?.toTransitionType()
        ?: RouterTransitionType.Horizontal
      conductorRouter.setBackstack(
        newBackstack, popTransition.toChangeHandler(isPush = false, overlayType = RouterOverlayType.None)
      )
      // see NOTE_ROUTE_MAPPING_CLEAN_UP
    } else {
      Timber.e("failed to popTo(${route.javaClass.simpleName}): no such route found")
    }
  }

  private fun createTransaction(route: Route, context: RouteContext): RouterTransaction {
    val controller = screenKeyFactory(route).createController(conductorRouter.activity!!, context.parentScopeName)
    return controller
      .obtainTransaction(context.transitionType, context.overlayType)
      // have to use 'path' here rather than route,
      // because conductor will save/restore only 'String' based tags on rotation
      .tag(route.path)
  }

  private fun RouterTransaction.addToControllerRoutes(route: Route): RouterTransaction {
    controllerRoutes[controller.instanceId] = route
    return this
  }

  private inner class ChangeListener : ControllerChangeHandler.ControllerChangeListener {
    override fun onChangeStarted(
      to: Controller?,
      from: Controller?,
      isPush: Boolean,
      container: ViewGroup,
      handler: ControllerChangeHandler,
    ) {
      for (observer in routeObservers) {
        val routeTo = if (to != null) controllerRoutes[to.instanceId] else null

        if (routeTo == null) {
          Timber.d("'to' controller is null, not reporting change to observers")
          continue
        }
        val routeFrom = if (from != null) controllerRoutes[from.instanceId] else null
        if (isPush) {
          observer.pushStarted(routeTo, routeFrom)
        } else {
          observer.popStarted(routeTo, routeFrom)
        }
      }
    }

    override fun onChangeCompleted(
      to: Controller?,
      from: Controller?,
      isPush: Boolean,
      container: ViewGroup,
      handler: ControllerChangeHandler,
    ) {
      for (observer in routeObservers) {
        val routeTo = if (to != null) controllerRoutes[to.instanceId] else null
        if (routeTo == null) {
          Timber.d("'to' controller is null, not reporting change to observers")
          continue
        }
        val routeFrom = if (from != null) controllerRoutes[from.instanceId] else null
        if (isPush) {
          observer.pushCompleted(routeTo, routeFrom)
        } else {
          observer.popCompleted(routeTo, routeFrom)
        }
        // see NOTE_ROUTE_MAPPING_CLEAN_UP
        cleanupOldRoutes()
      }
    }

    private fun cleanupOldRoutes() {
      val sz = controllerRoutes.size
      controllerRoutes.keys.retainAll(
        conductorRouter.backstack.map { it.controller.instanceId }
      )
      if (sz - controllerRoutes.size > 0) {
        Timber.d("cleaned up ${sz - controllerRoutes.size} old routes")
      }
    }
  }
}

/**
 * Проверяет, нет ли нового роута в конце бэкстека, если есть, то новая команда никуда не ведет.
 * Нужно, чтобы исключить ситуации, когда в конец стека случайно попадают одинаковые роуты.
 * Например, такое может произойти при быстрых кликах по кнопкам.
 */
private fun Route.toCommandSafe(
  backstack: List<RouterTransaction>,
  safeRoutingCommand: (Route) -> Unit,
) {
  val newRoute = this
  if (backstack.lastOrNull()?.tag() != newRoute.path) {
    if (BuildConfig.DEBUG) {
      checkRoutePathUniqueness(newRoute, backstack)
    }
    safeRoutingCommand.invoke(newRoute)
  } else {
    Timber.e("tried add same route $newRoute, just ignore it")
  }
}

/**
 * Checks that newly added route has a unique path.
 * This Router implementation relies on route path uniqueness in several methods, for example [ConductorAppRouter.popTo]
 */
private fun checkRoutePathUniqueness(newRoute: Route, backstack: List<RouterTransaction>) {
  check(backstack.none { it.tag() == newRoute.path }) {
    val position = backstack.indexOfFirst { it.tag() == newRoute.path }
    "Route path must be unique, backstack contains duplicate path at position $position: ${newRoute.path}"
  }
}

// NOTE_ROUTE_MAPPING_CLEAN_UP
// Mappings from controller instances to their route paths are kept until transition completes,
// i.e. during a transition route mappings can contain instances of controllers which are not
// in backstack anymore. This is done to simplify implementation of routing observers to not
// keep track of from/to controller route mappings when pop is in progress or vice versa

// NOTE_ROUTER_AND_SAVED_INSTANCE_STATE
//
// Примечание. Изменения сделаны в данном файле, а не где-нибудь в MainActivity, потому что описанное поведение
// специфично для Conductor, этот router как раз содержит conductor-based реализацию.
//
// В Conductor-е есть поддержка восстановления backstack в двух ситуациях:
// 1. Когда activity рестартует (вызывается onCreate()) и при этом не рестартует весь процесс.
//    Восстановление происходит из внутреннего static-cache.
//    Например это происходит при смене языка в настройках
// 2. Когда activity рестартует (вызывается onCreate()) и при этом рестартует весь процесс.
//    Восстановление происходит из savedInstanceState.
//    Например это происходит при отнимании выданного ранее permission (Camera)
//
// Во втором случае всё оказывается плохо, потому что восстановление стэка часто будет приводить к отображению
// какого-либо контроллера, который находился глубоко в неком флоу без  корректного восстановления
// всего дерева модулей DI дерева. Это приводит к падениям вроде этого: https://j.appkode.ru/browse/TBI-246.
// Поэтому решение здесь такое: не передавать savedInstanceState внутрь кондуктора.
//
// В первом случае, обычно, DI-дерево всё ещё в памяти, поэтому МП успевает стартануть и поставить корректный
// root-контроллер, крэша не происходит. Хотя, здесь тоже возможны не очень хорошие сценарии, зависящие от конкретных
// фич, их нужно будет выявить в процессе разработки с помощью QA. Если таковые объявятся, то нужно будет
// насильно очищать backstack после attachRouter

// NOTE_ROUTER_NOT_USING_ATTACH_ROUTER
// При создании роутера не используется стандартная для этого дела функция Conductor.attachRouter(...),
// потому что это приводит к тому, что если кондуктор успел сохранить бэк-стек, то он будет безоговорочно
// его пытаться восстановить через свою внутреннюю функцию rebindIfNeeded(). Поэтому мы копируем
// код из attachRouter() убирая из него rebindIfNeeded() — нам никогда не нужно...
