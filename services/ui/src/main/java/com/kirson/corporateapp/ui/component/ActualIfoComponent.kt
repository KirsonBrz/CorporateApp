package com.kirson.corporateapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun ActualIfoComponent(
    modifier: Modifier = Modifier,
    text: String,
    background: Int
) {
  Card(
      modifier = modifier
          .padding(horizontal = 4.dp)
          .width(300.dp)
          .padding(horizontal = 1.dp, vertical = 8.dp),
      elevation = 8.dp,
      backgroundColor = AppTheme.colors.backgroundPrimary,
      shape = RoundedCornerShape(12.dp)
  ) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
      Image(
          modifier = Modifier.fillMaxWidth(),
          painter = painterResource(id = background),
          contentDescription =  "",
          contentScale = ContentScale.FillBounds
      )
      Text(
          modifier = modifier
              .padding(horizontal = 16.dp, vertical = 28.dp),
          text = text,
          style = AppTheme.typography.bodyRegular,
          textAlign = TextAlign.Start,
          maxLines = 3,
          color = AppTheme.colors.backgroundPrimary
      )
    }

  }
}