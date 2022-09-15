package com.kirson.corporateapp.core.util

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.kirson.corporateapp.core.model.SCREEN_KEY_ARG_NAME
import com.kirson.corporateapp.core.model.ScreenKey
import com.kirson.corporateapp.core.routing.transition.RouterOverlayType
import com.kirson.corporateapp.core.routing.transition.RouterTransitionType

inline val Controller.requireResources get() = this.resources!!
inline val Controller.requireActivity get() = this.activity!!

fun <T : ScreenKey> Controller.key(): T {
  return args.getParcelable(SCREEN_KEY_ARG_NAME)
    ?: error("controller was not constructed with key")
}

private const val SLIDE_BOTTOM_PUSH_ANIM_DURATION = 300L
private const val SLIDE_BOTTOM_POP_ANIM_DURATION = 200L
private const val SLIDE_HORIZONTAL_PUSH_ANIM_DURATION = 300L
private const val SLIDE_HORIZONTAL_POP_ANIM_DURATION = 200L
private const val SLIDE_FADE_PUSH_ANIM_DURATION = 200L
private const val SLIDE_FADE_POP_ANIM_DURATION = 200L

@Suppress("ComplexMethod") // допустимая сложность метода: single when
fun RouterTransitionType.toChangeHandler(
  isPush: Boolean,
  overlayType: RouterOverlayType
): ControllerChangeHandler {
  return when (this) {
    is RouterTransitionType.Horizontal -> {
      val duration = if (isPush) SLIDE_HORIZONTAL_PUSH_ANIM_DURATION else SLIDE_HORIZONTAL_POP_ANIM_DURATION
      HorizontalChangeHandler(duration, removesFromViewOnPush(overlayType))
    }
    is RouterTransitionType.Vertical -> {
      val duration = if (isPush) SLIDE_BOTTOM_PUSH_ANIM_DURATION else SLIDE_BOTTOM_POP_ANIM_DURATION
      VerticalChangeHandler(duration, removesFromViewOnPush(overlayType))
    }
    is RouterTransitionType.Fade -> {
      val duration = if (isPush) SLIDE_FADE_PUSH_ANIM_DURATION else SLIDE_FADE_POP_ANIM_DURATION
      FadeChangeHandler(duration, removesFromViewOnPush(overlayType))
    }
    is RouterTransitionType.None -> {
      SimpleSwapChangeHandler(removesFromViewOnPush(overlayType))
    }
  }
}

fun ControllerChangeHandler.toTransitionType(): RouterTransitionType {
  return when (this) {
    is HorizontalChangeHandler -> RouterTransitionType.Horizontal
    is VerticalChangeHandler -> RouterTransitionType.Vertical
    is FadeChangeHandler -> RouterTransitionType.Fade
    is SimpleSwapChangeHandler -> RouterTransitionType.None
    else -> RouterTransitionType.None
  }
}

fun Controller.obtainTransaction(
  transitionType: RouterTransitionType,
  overlayType: RouterOverlayType = RouterOverlayType.None
): RouterTransaction {
  return RouterTransaction.with(this)
    .pushChangeHandler(transitionType.toChangeHandler(isPush = true, overlayType = overlayType))
    .popChangeHandler(transitionType.toChangeHandler(isPush = false, overlayType = overlayType))
}

private fun removesFromViewOnPush(overlayType: RouterOverlayType): Boolean {
  return when (overlayType) {
    RouterOverlayType.None -> true
    RouterOverlayType.Popup -> false
  }
}
