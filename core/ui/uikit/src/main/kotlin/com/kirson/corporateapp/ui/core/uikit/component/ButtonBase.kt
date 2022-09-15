package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme



@Immutable
data class ButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
) : androidx.compose.material.ButtonColors {

  @Composable
  override fun backgroundColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
  }

  @Composable
  override fun contentColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
  }
}