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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.services.ui.R
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun DocumentTemplateComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onDocumentClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
  Card(
      modifier = modifier
          .clickable(onClick = onDocumentClick)
          .padding(vertical = 8.dp, horizontal = 16.dp),
      shape = RoundedCornerShape(12.dp),
      elevation = 8.dp,
      backgroundColor = AppTheme.colors.backgroundPrimary
  ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colors.backgroundPrimary,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
      Column(
          verticalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.weight(1f)
      ) {
        Text(
            text = title,
            style = AppTheme.typography.subtitle,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = AppTheme.typography.body1,
            textAlign = TextAlign.Start,
        )
      }
      Spacer(modifier = Modifier.weight(0.2f))
      Icon(
          modifier = modifier
              .weight(0.2f)
              .padding(all = 6.dp)
              .align(Alignment.CenterVertically)
              .clickable(onClick = onDownloadClick)
              .background(color = AppTheme.colors.backgroundSecondary, shape = RoundedCornerShape(12.dp)),
          painter = painterResource(id = R.drawable.ic_download_24),
          contentDescription = ""
      )
      }
  }
}