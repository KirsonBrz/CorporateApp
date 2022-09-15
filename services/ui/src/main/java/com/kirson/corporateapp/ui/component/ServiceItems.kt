package com.kirson.corporateapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun ServiceItemCard(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    background: Int,
    onClick: () -> Unit
) {
  Card(
      modifier = modifier
          .height(108.dp)
          .clickable(onClick = onClick)
          .padding(all = 4.dp),
      shape = RoundedCornerShape(12.dp),
      backgroundColor = AppTheme.colors.backgroundPrimary,
      elevation = 12.dp
  ) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = background))
            .padding(all = 2.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
      Icon(
          painter = icon,
          tint = AppTheme.colors.backgroundPrimary,
          contentDescription = "icon",
          modifier = Modifier
              .padding(start = 15.dp, bottom = 13.dp)
              .scale(1.25F)
      )
      Text(
          text = title,
          style = AppTheme.typography.body1,
          textAlign = TextAlign.Center,
          color = AppTheme.colors.backgroundPrimary,
          modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(top = 13.dp)

      )
    }
  }
}
