package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun ErrorSnackbar(
  modifier: Modifier = Modifier,
  message: String,
) {
  Snackbar(
    modifier = modifier,
    shape = RoundedCornerShape(13.dp),
    backgroundColor = AppTheme.colors.indicatorContendError,
  ) {
    Text(
      modifier = Modifier.padding(vertical = 10.dp),
      text = message,
      style = AppTheme.typography.caption1,
      color = AppTheme.colors.textButton,
    )
  }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun ErrorSnackbarPreview() {
  AppTheme {
    Column {
      ErrorSnackbar(
        modifier = Modifier.padding(16.dp),
        message = "Message"
      )
      ErrorSnackbar(
        modifier = Modifier.padding(16.dp),
        message = "Long Message Long Message Long Message  Long Message"
      )
    }
  }
}
