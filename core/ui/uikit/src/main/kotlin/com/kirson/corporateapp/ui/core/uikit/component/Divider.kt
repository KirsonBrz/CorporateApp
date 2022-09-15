package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun HorizontalDivider(
  modifier: Modifier = Modifier,
  color: Color = AppTheme.colors.backgroundPrimary,
  thickness: Dp = 1.dp,
  startIndent: Dp = 0.dp,
  endIndent: Dp = 0.dp,
) {
  val indentMod = if (startIndent.value != 0f || endIndent.value != 0f) {
    Modifier.padding(start = startIndent, end = endIndent)
  } else {
    Modifier
  }
  Box(
    modifier.then(indentMod)
      .fillMaxWidth()
      .height(thickness)
      .background(color = color)
  )
}

@Composable
fun VerticalDivider(
  modifier: Modifier = Modifier,
  color: Color = AppTheme.colors.backgroundPrimary,
  thickness: Dp = 1.dp,
  topIndent: Dp = 0.dp,
  bottomIndent: Dp = 0.dp,
) {
  val indentMod = if (topIndent.value != 0f || bottomIndent.value != 0f) {
    Modifier.padding(top = topIndent, bottom = bottomIndent)
  } else {
    Modifier
  }
  Box(
    modifier.then(indentMod)
      .fillMaxHeight()
      .height(1.dp)
      .width(thickness)
      .background(color = color)
  )
}
