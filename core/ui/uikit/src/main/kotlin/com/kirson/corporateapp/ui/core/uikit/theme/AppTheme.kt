package com.kirson.corporateapp.ui.core.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kirson.corporateapp.ui.core.uikit.theme.AppColors

@Composable
fun AppTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  typography: AppTypography = AppTheme.typography,
  content: @Composable () -> Unit,
) {
  val colors = if (useDarkTheme) DarkColorPalette else LightColorPalette
  val colorPalette = remember { colors }
  colorPalette.update(colors)

  val sysUiController = rememberSystemUiController()
  SideEffect {
    sysUiController.setSystemBarsColor(
      color = Color.Transparent,
      darkIcons = true,
    )
  }

  MaterialTheme(
    colors = debugColors(),
    typography = MaterialTypography,
  ) {
    CompositionLocalProvider(
      LocalAppColors provides colorPalette,
      LocalAppTypography provides typography,
      LocalContentColor provides colors.textPrimary,
      LocalTextSelectionColors provides textSelectionColors(colors),
      LocalRippleTheme provides AppRippleTheme(colors),
      content = content,
    )
  }
}

private fun textSelectionColors(colors: AppColors): TextSelectionColors {
  return TextSelectionColors(
    handleColor = colors.contendAccentPrimary,
    backgroundColor = colors.contendAccentPrimary.copy(alpha = 0.4f)
  )
}

object AppTheme {
  val colors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColors.current

  val typography: AppTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalAppTypography.current
}

private val LocalAppColors = staticCompositionLocalOf<AppColors> {
  error("No LocalAppColors provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [.colors].
 */
fun debugColors() = Colors(
  primary = DebugColor,
  primaryVariant = DebugColor,
  secondary = DebugColor,
  secondaryVariant = DebugColor,
  background = DebugColor,
  surface = DebugColor,
  error = DebugColor,
  onPrimary = DebugColor,
  onSecondary = DebugColor,
  onBackground = DebugColor,
  onSurface = DebugColor,
  onError = DebugColor,
  isLight = true
)

private val DebugColor = Color.Magenta
