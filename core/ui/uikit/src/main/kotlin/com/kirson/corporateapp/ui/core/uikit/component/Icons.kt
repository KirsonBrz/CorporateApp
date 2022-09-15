package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kirson.corporateapp.core.ui.uikit.R
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun CloseIcon(
  modifier: Modifier = Modifier,
) {
  val resource = if (AppTheme.colors.isDark) {
    R.drawable.ic_close_round_secondary_light_24
  } else {
    R.drawable.ic_close_round_secondary_dark_24
  }
  Image(
    modifier = modifier,
    painter = painterResource(resource),
    contentDescription = "closeButton"
  )
}

@Composable
fun BackIcon() {
  Icon(
    painter = painterResource(id = R.drawable.ic_arrow_back_24),
    tint = AppTheme.colors.textPrimary,
    contentDescription = "backButton"
  )
}
