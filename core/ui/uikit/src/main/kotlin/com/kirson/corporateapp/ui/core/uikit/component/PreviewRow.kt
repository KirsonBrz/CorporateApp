package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PreviewRow(title: String, content: @Composable () -> Unit) {
  Column(
    modifier = Modifier
      .drawBehind {
        val strokeWidth = 2.dp.toPx()
        drawRect(
          color = Color.LightGray,
          topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
          size = size.copy(width = size.width - strokeWidth / 2, height = size.height - strokeWidth / 2),
          style = Stroke(
            width = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 4.dp.toPx()))
          )
        )
      }
      .padding(16.dp)
  ) {
    Text(text = title)
    Spacer(modifier = Modifier.size(8.dp))
    content()
  }
}
