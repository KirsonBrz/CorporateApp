package com.kirson.corporateapp.core.routing.transition

sealed class RouterTransitionType {
  object Horizontal : RouterTransitionType()
  object Vertical : RouterTransitionType()
  object Fade : RouterTransitionType()
  object None : RouterTransitionType()
}
