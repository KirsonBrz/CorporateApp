package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun PrimaryButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String,
  enabled: Boolean = true,
) {
  Button(
    modifier = modifier,
    onClick = onClick,
    text = text,
    enabled = enabled,
    colors = ButtonDefaults.primaryButtonColors(),
    elevation = androidx.compose.material.ButtonDefaults.elevation(
      defaultElevation = 8.dp,
      pressedElevation = 8.dp,
      disabledElevation = 0.dp,
    )
  )
}


@Suppress("LongMethod") // complex composable
@Composable
internal fun Button(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String,
  enabled: Boolean = true,
  colors: ButtonColors = ButtonDefaults.primaryButtonColors(),
  elevation: ButtonElevation = androidx.compose.material.ButtonDefaults
    .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
) {
  return Button(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled,
    colors = colors,
    elevation = elevation,
  ) {
    ButtonText(text)
  }
}


@Composable
internal fun Button(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  enabled: Boolean = true,
  colors: ButtonColors = ButtonDefaults.primaryButtonColors(),
  elevation: ButtonElevation = androidx.compose.material.ButtonDefaults
    .elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
  content: @Composable RowScope.() -> Unit,
) {
  androidx.compose.material.Button(
    onClick,
    modifier.heightIn(52.dp),
    enabled,
    colors = colors,
    shape = RoundedCornerShape(26.dp),
    elevation = elevation
  ) {
    val contentColor by colors.contentColor(enabled = enabled)
    CompositionLocalProvider(LocalContentColor provides contentColor) {
      content()
    }
  }
}

@Composable
private fun ButtonText(
  text: String,
) {
  Text(
    textAlign = TextAlign.Center,
    style = AppTheme.typography.button,
    text = text,
  )
}

internal object ButtonDefaults {
  @Composable
  fun primaryButtonColors(): ButtonColors {
    return KodeBankButtonColors(
      backgroundColor = AppTheme.colors.primaryButton,
      contentColor = AppTheme.colors.textButton,
      disabledBackgroundColor = AppTheme.colors.primaryButton,
      disabledContentColor = AppTheme.colors.textSecondary,
    )
  }
}

@Immutable
data class KodeBankButtonColors(
  private val backgroundColor: Color,
  private val contentColor: Color,
  private val disabledBackgroundColor: Color,
  private val disabledContentColor: Color,
) : ButtonColors {

  @Composable
  override fun backgroundColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
  }

  @Composable
  override fun contentColor(enabled: Boolean): State<Color> {
    return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
  }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun PrimaryButtonPreview() {
  Column {
    PrimaryButton(
      modifier = Modifier.fillMaxWidth(),
      onClick = { /*TODO*/ },
      text = "Кнопка",
      enabled = true
    )
    Spacer(modifier = Modifier.height(16.dp))
    PrimaryButton(
      modifier = Modifier.fillMaxWidth(),
      onClick = { /*TODO*/ },
      text = "Кнопка",
      enabled = true
    )
  }
}
