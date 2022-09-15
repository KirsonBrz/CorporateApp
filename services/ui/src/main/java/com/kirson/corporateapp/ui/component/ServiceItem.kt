package com.kirson.corporateapp.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun ServiceItem() {
  Card(
      modifier = Modifier,
      shape = RoundedCornerShape(12.dp),
      backgroundColor = AppTheme.colors.backgroundPrimary,
      elevation = 12.dp
  ) {

  }
}