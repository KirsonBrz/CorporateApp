package com.kirson.corporateapp.core.routing

interface Router {
  fun setRoot(route: Route, context: RouteContext)
  /**
   * Push the given route onto the router and then remove all the previous routes
   * until the [beforePushClearUntil] route is encountered (exclusive).
   * If [beforePushClearUntil] is null, controller is simply pushed onto a backstack
   *
   * @param beforePushClearUntil receives a route on top of which new route should be pushed. All
   * routes on top of this route in the backstack are removed prior to pushing [route]
   */
  fun push(
    route: Route,
    context: RouteContext,
    beforePushClearUntil: Route? = null,
  )

  fun pop()
  fun popTo(route: Route)

  fun addRouteObserver(observer: RouteObserver<Route>)
  fun removeRouteObserver(observer: RouteObserver<Route>)

  /**
   * **WARNING** Use this knowledge very carefully and be sure not to use `popTo`
   * which would jump to a screen which was before initial route of the current Coordinator
   */
  val backstack: List<Route>
}

interface Route {
  val path: String
}
