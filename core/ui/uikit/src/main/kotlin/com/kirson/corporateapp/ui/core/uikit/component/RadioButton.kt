package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun RadioButton(
    selected: Boolean,
    onClick: () -> Unit
) {
  androidx.compose.material.RadioButton(
      selected = selected,
      onClick = onClick,
      colors = RadioButtonDefaults.colors(
          selectedColor = AppTheme.colors.contendAccentPrimary,
          unselectedColor = AppTheme.colors.backgroundPrimary
      )
  )
}