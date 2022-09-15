package com.kirson.corporateapp.ui.core.uikit.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kirson.corporateapp.ui.core.uikit.theme.AppColors

internal class AppRippleTheme(private val colors: AppColors): RippleTheme {

  @Composable
  override fun defaultColor(): Color {
    return RippleTheme.defaultRippleColor(
      colors.contendAccentPrimary,
      lightTheme = !colors.isDark
    )
  }

  @Composable
  override fun rippleAlpha(): RippleAlpha {
    return RippleTheme.defaultRippleAlpha(
      colors.contendAccentPrimary,
      lightTheme = !colors.isDark
    )
  }
}