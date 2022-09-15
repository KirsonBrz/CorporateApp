package com.kirson.corporateapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.auth.ui.R
import com.kirson.corporateapp.ui.core.uikit.component.MaskVisualTransformation
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
internal fun TextField(
  modifier: Modifier = Modifier,
  inputValue: String,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  placeholder: String? = null,
  isError: Boolean = false,
  isEnabled: Boolean = true,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions(),
  onValueChange: (String) -> Unit,
) {
  TextField(
    modifier = modifier
      .heightIn(min = 52.dp)
      .fillMaxWidth(),
    value = inputValue,
    onValueChange = onValueChange,
    textStyle = AppTheme.typography.body2,
    label = null,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    isError = isError,
    enabled = isEnabled,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = true,
    maxLines = 1,
    placeholder = placeholder?.let {
      {
        Text(
          text =  it,
          style = AppTheme.typography.body2,
          color = AppTheme.colors.textTertiary
        )
      }
    },
    shape = RoundedCornerShape(26.dp),
    colors = textFieldColors(
      backgroundColor = AppTheme.colors.contendPrimary,
      textColor = AppTheme.colors.textPrimary,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      errorIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      cursorColor = AppTheme.colors.contendAccentPrimary,
      placeholderColor = AppTheme.colors.contendAccentPrimary,
      disabledPlaceholderColor = AppTheme.colors.contendAccentPrimary,
    )
  )
}

@Composable
@Preview
private fun TextFieldPreview() {
  AppTheme {
    TextField(
      inputValue = "",
      onValueChange = { },
      leadingIcon = {
        Icon(
          modifier = Modifier.padding(start = 16.dp),
          painter = painterResource(id = R.drawable.ic_phone_24),
          contentDescription = "phone icon",
          tint = AppTheme.colors.contendAccentPrimary
        )
      },
      placeholder = "Телефон",
      visualTransformation = MaskVisualTransformation(mask = "+7 (***) *** ** **"),
    )
  }
}