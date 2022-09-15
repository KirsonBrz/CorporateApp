package com.kirson.corporateapp.core.routing

import com.kirson.corporateapp.core.routing.transition.RouterOverlayType
import com.kirson.corporateapp.core.routing.transition.RouterTransitionType

data class RouteContext(
  val parentScopeName: String,
  val transitionType: RouterTransitionType = RouterTransitionType.Horizontal,
  val overlayType: RouterOverlayType = RouterOverlayType.None
)
