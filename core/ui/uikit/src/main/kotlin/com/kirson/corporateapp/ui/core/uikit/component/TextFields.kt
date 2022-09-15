package com.mapswithme.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.component.CloseIcon
import com.kirson.corporateapp.ui.core.uikit.component.PreviewRow
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

/**
 * Filled text field.
 *
 * @param modifier modifier to apply
 * @param value field value
 * @param onValueChange value change listener
 * @param enabled enabled state
 * @param readOnly read only state
 * @param label an optional label. Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param placeholder an optional placeholder to display if field is not filled
 * @param helperText an optional helper text. Will use error color if [isError] is `true`.
 *        Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param leadingContent a slot for leading content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param trailingContent a slot for trailing content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param isError error state
 * @param visualTransformation a visual transformation to apply
 * @param keyboardOptions keyboard options
 * @param keyboardActions available keyboard actions
 * @param singleLine whether to use a single line mode
 * @param maxLines max available lines for wrapping
 */
@Composable
fun TextField(
  modifier: Modifier = Modifier,
  value: TextFieldValue,
  onValueChange: (TextFieldValue) -> Unit,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  label: String? = null,
  placeholder: String? = null,
  helperText: String? = null,
  leadingContent: @Composable (() -> Unit)? = null,
  trailingContent: @Composable ((defaultMinSize: Dp) -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions(),
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  singleLine: Boolean = false,
  maxLines: Int = Int.MAX_VALUE,
) {
  Column(
    modifier = modifier,
  ) {
    val errorColor = AppTheme.colors.indicatorContendError
    val fieldShape = RoundedCornerShape(12.dp)
    if (label != null) {
      Text(
        modifier = Modifier
          .height(20.dp),
        text = label,
        color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.contendTertiary,
        style = AppTheme.typography.body1
      )
      Spacer(modifier = Modifier.height(4.dp))
    }
    val colors = textFieldColors(readOnly = readOnly)
    BasicTextField(
      value = value,
      onValueChange = onValueChange,
      enabled = enabled,
      readOnly = readOnly,
      textStyle = AppTheme.typography.body1.copy(color = colors.textColor(enabled = enabled).value),
      visualTransformation = visualTransformation,
      keyboardOptions = keyboardOptions,
      keyboardActions = keyboardActions,
      singleLine = singleLine,
      cursorBrush = SolidColor(colors.cursorColor(isError).value),
      interactionSource = interactionSource,
      maxLines = maxLines,
    ) { innerTextField ->
      val defaultMinHeight = 40.dp
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .heightIn(min = defaultMinHeight)
          .background(
            color = colors.backgroundColor(enabled = enabled).value,
            shape = fieldShape
          )
          .border(
            width = 1.dp,
            color = if (isError) errorColor else Color.Transparent,
            shape = fieldShape,
          ),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        if (leadingContent != null) {
          CompositionLocalProvider(
            LocalContentColor provides colors.leadingIconColor(
              enabled = enabled,
              isError = isError
            ).value
          ) {
            Box(
              modifier = Modifier.defaultMinSize(
                minWidth = defaultMinHeight,
                minHeight = defaultMinHeight
              ),
              contentAlignment = Alignment.Center,
            ) {
              leadingContent()
            }
          }
        }
        Box(
          modifier = Modifier
            .weight(1f)
            .padding(
              start = if (leadingContent == null) 12.dp else 0.dp,
              end = if (trailingContent != null) 12.dp else 0.dp
            )
        ) {
          if (value.text.isEmpty()) {
            placeholder?.let {
              Text(
                text = it,
                color = colors.placeholderColor(enabled = enabled).value,
                style = AppTheme.typography.body1
              )
            }
          }
          innerTextField()
        }
        if (trailingContent != null) {
          CompositionLocalProvider(
            LocalContentColor provides colors.trailingIconColor(
              enabled = enabled,
              isError = isError
            ).value
          ) {
            Box(
              modifier = Modifier.size(
                width = defaultMinHeight,
                height = defaultMinHeight
              ),
              contentAlignment = Alignment.Center,
            ) {
              trailingContent(defaultMinHeight)
            }
          }
        }
      }
    }
    if (helperText != null) {
      Text(
        modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp),
        text = helperText,
        color = when {
          isError -> errorColor
          !enabled -> AppTheme.colors.contendTertiary
          else -> AppTheme.colors.textSecondary
        },
        style = AppTheme.typography.caption1
      )
    }
  }
}

/**
 * Filled text field.
 *
 * @param modifier modifier to apply
 * @param value field value
 * @param onValueChange value change listener
 * @param enabled enabled state
 * @param readOnly read only state
 * @param label an optional label. Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param placeholder an optional placeholder to display if field is not filled
 * @param helperText an optional helper text. Will use error color if [isError] is `true`.
 *        Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param leadingContent a slot for leading content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param trailingContent a slot for trailing content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param isError error state
 * @param visualTransformation a visual transformation to apply
 * @param keyboardOptions keyboard options
 * @param keyboardActions available keyboard actions
 * @param singleLine whether to use a single line mode
 * @param maxLines max available lines for wrapping
 */
@Composable
fun TextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  label: String? = null,
  placeholder: String? = null,
  helperText: String? = null,
  leadingContent: @Composable (() -> Unit)? = null,
  trailingContent: @Composable ((defaultMinSize: Dp) -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions(),
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  singleLine: Boolean = false,
  maxLines: Int = Int.MAX_VALUE,
) {
  // This idea is copied from the similar String-value overload of underlying material.TextField
  var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
  val textFieldValue = textFieldValueState.copy(text = value)
  TextField(
    modifier = modifier,
    value = textFieldValue,
    onValueChange = { fieldValue ->
      textFieldValueState = fieldValue
      if (value != fieldValue.text) {
        onValueChange(fieldValue.text)
      }
    },
    enabled = enabled,
    readOnly = readOnly,
    label = label,
    placeholder = placeholder,
    helperText = helperText,
    leadingContent = leadingContent,
    trailingContent = trailingContent,
    isError = isError,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    interactionSource = interactionSource,
    singleLine = singleLine,
    maxLines = maxLines
  )
}

/**
 * Filled text area. The main difference between the text field above is that here you can specify
 * min lines value which will affect the min height of the text area. Next is that this component
 * has different paddings, because the text field in appearance intended only for the search field.
 * todo refactor text field: select a common component on the basis of which individual one https://convexitydmcc.atlassian.net/browse/MAPS-1223
 *
 * @param modifier modifier to apply
 * @param value field value
 * @param onValueChange value change listener
 * @param enabled enabled state
 * @param readOnly read only state
 * @param label an optional label. Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param placeholder an optional placeholder to display if field is not filled
 * @param helperText an optional helper text. Will use error color if [isError] is `true`.
 *        Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param counterEnabled an optional chars counter
 * @param counterMaxLength an optional max value for chars counter.
 *        If it specified, the counter will be rendered in the "valueLength / maxLength" format
 * @param leadingContent a slot for leading content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param isError error state
 * @param visualTransformation a visual transformation to apply
 * @param keyboardOptions keyboard options
 * @param keyboardActions available keyboard actions
 * @param singleLine whether to use a single line mode
 * @param minLines min available lines for wrapping. Will not work together with [singleLine]
 * @param maxLines max available lines for wrapping
 */
@Composable
fun TextArea(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  label: String? = null,
  placeholder: String? = null,
  helperText: String? = null,
  counterEnabled: Boolean = false,
  counterMaxLength: Int? = null,
  leadingContent: @Composable (() -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  singleLine: Boolean = false,
  minLines: Int = 1,
  maxLines: Int = Int.MAX_VALUE,
) {
  // This idea is copied from the similar String-value overload of underlying material.TextField
  var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
  val textFieldValue = textFieldValueState.copy(text = value)
  TextArea(
    modifier = modifier,
    value = textFieldValue,
    onValueChange = { fieldValue ->
      textFieldValueState = fieldValue
      if (value != fieldValue.text) {
        onValueChange(fieldValue.text)
      }
    },
    enabled = enabled,
    readOnly = readOnly,
    label = label,
    placeholder = placeholder,
    helperText = helperText,
    leadingContent = leadingContent,
    isError = isError,
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    interactionSource = interactionSource,
    singleLine = singleLine,
    minLines = minLines,
    maxLines = maxLines,
    counterMaxLength = counterMaxLength,
    counterEnabled = counterEnabled
  )
}

/**
 * Filled text area. The main difference between the text field above is that here you can specify
 * min lines value which will affect the min height of the text area. Next is that this component
 * has different paddings, because the text field in appearance intended only for the search field.
 * todo refactor text field: select a common component on the basis of which individual one https://convexitydmcc.atlassian.net/browse/MAPS-1223
 *
 * @param modifier modifier to apply
 * @param value field value
 * @param onValueChange value change listener
 * @param enabled enabled state
 * @param readOnly read only state
 * @param label an optional label. Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param placeholder an optional placeholder to display if field is not filled
 * @param helperText an optional helper text. Will use error color if [isError] is `true`.
 *        Pass an empty string if you want to "reserve" a corresponding place in layout
 * @param counterEnabled an optional chars counter
 * @param counterMaxLength an optional max value for chars counter.
 *        If it specified, the counter will be rendered in the "valueLength / maxLength" format
 * @param leadingContent a slot for leading content.
 *        Has a **default min size** of 48.dp, you may need to add padding if your content exceeds this.
 *        Will have a **content color** assigned according to enabled/readOnly/error state.
 *        Use `LocalContentColor.current` to obtain it
 * @param isError error state
 * @param visualTransformation a visual transformation to apply
 * @param keyboardOptions keyboard options
 * @param keyboardActions available keyboard actions
 * @param singleLine whether to use a single line mode
 * @param minLines min available lines for wrapping. Will not work together with [singleLine]
 * @param maxLines max available lines for wrapping
 */
@Suppress("ComplexMethod") // Component has a lot of states
@Composable
fun TextArea(
  modifier: Modifier = Modifier,
  value: TextFieldValue,
  onValueChange: (TextFieldValue) -> Unit,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  label: String? = null,
  placeholder: String? = null,
  helperText: String? = null,
  counterEnabled: Boolean = false,
  counterMaxLength: Int? = null,
  leadingContent: @Composable (() -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  singleLine: Boolean = false,
  minLines: Int = 1,
  maxLines: Int = Int.MAX_VALUE
) {
  val heightState = remember { mutableStateOf<Int?>(null) }
  var heightUpdateNeeded by remember(maxLines) { mutableStateOf(true) }
  val height = with(LocalDensity.current) { heightState.value?.toDp() }
  Column(modifier = modifier) {
    val errorColor = AppTheme.colors.indicatorContendError
    val fieldShape = RoundedCornerShape(12.dp)
    val colors = textFieldColors(readOnly = readOnly)
    val textStyle = AppTheme.typography.body1.copy(color = colors.textColor(enabled = enabled).value)
    if (label != null) {
      Text(
        text = label,
        color = if (enabled) AppTheme.colors.textPrimary else AppTheme.colors.contendTertiary,
        style = AppTheme.typography.body1
      )
      Spacer(modifier = Modifier.height(4.dp))
    }
    Box(
      modifier = Modifier
        .padding(vertical = 2.dp)
        .height(IntrinsicSize.Min)
        .fillMaxWidth()
    ) {
      if (heightUpdateNeeded) {
        BasicTextField(
          modifier = Modifier
            .fillMaxSize()
            .alpha(0f)
            .onSizeChanged { size ->
              heightUpdateNeeded = false
              heightState.value = size.height
            },
          value = TextFieldValue(value.text + "\n".repeat(minLines)),
          onValueChange = onValueChange,
          enabled = enabled,
          readOnly = readOnly,
          textStyle = textStyle,
          visualTransformation = visualTransformation,
          keyboardOptions = keyboardOptions,
          keyboardActions = keyboardActions,
          singleLine = singleLine,
          maxLines = maxLines,
          interactionSource = interactionSource
        )
      }
      if (height != null) {
        BasicTextField(
          value = value,
          onValueChange = onValueChange,
          enabled = enabled,
          readOnly = readOnly,
          textStyle = textStyle,
          visualTransformation = visualTransformation,
          keyboardOptions = keyboardOptions,
          keyboardActions = keyboardActions,
          cursorBrush = SolidColor(colors.cursorColor(isError).value),
          interactionSource = interactionSource,
          singleLine = singleLine,
          maxLines = maxLines,
        ) { innerTextField ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(
                color = colors.backgroundColor(enabled = enabled).value,
                shape = fieldShape
              )
              .border(
                width = 1.dp,
                color = if (isError) errorColor else Color.Transparent,
                shape = fieldShape,
              )
              .padding(horizontal = 12.dp, vertical = 10.dp)
          ) {
            if (leadingContent != null) {
              Column {
                leadingContent()
              }
              Spacer(modifier = Modifier.width(8.dp))
            }
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .height(height)
            ) {
              if (value.text.isEmpty() && placeholder != null) {
                Text(
                  text = placeholder,
                  color = colors.placeholderColor(enabled = enabled).value,
                  style = AppTheme.typography.body1
                )
              }
              innerTextField()
            }
          }
        }
      }
    }
    if (helperText != null || counterEnabled) {
      Row(modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp)) {
        if (helperText != null) {
          Text(
            text = helperText,
            color = when {
              isError -> errorColor
              !enabled -> AppTheme.colors.contendTertiary
              else -> AppTheme.colors.textSecondary
            },
            style = AppTheme.typography.caption2
          )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (counterEnabled) {
          Text(
            modifier = Modifier.padding(start = 24.dp),
            text = if (counterMaxLength != null) {
              "${value.text.length} / $counterMaxLength"
            } else {
              value.text.length.toString()
            },
            style = AppTheme.typography.caption2,
            color = if (enabled) AppTheme.colors.textSecondary else AppTheme.colors.contendTertiary
          )
        }
      }
    }
  }
}

@Composable
private fun textFieldColors(readOnly: Boolean): TextFieldColors {
  val colors = AppTheme.colors
  return TextFieldDefaults.textFieldColors(
    textColor = if (readOnly) colors.contendTertiary else colors.textPrimary,
    disabledTextColor = colors.contendTertiary,
    cursorColor = colors.contendAccentPrimary,
    errorCursorColor = colors.indicatorContendError,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    leadingIconColor = if (readOnly) colors.contendTertiary else colors.textPrimary,
    disabledLeadingIconColor = colors.contendTertiary,
    errorLeadingIconColor = colors.indicatorContendError,
    trailingIconColor = if (readOnly) colors.contendTertiary else colors.textPrimary,
    disabledTrailingIconColor = colors.contendTertiary,
    errorTrailingIconColor = colors.indicatorContendError,
    backgroundColor = colors.contendSecondary,
    focusedLabelColor = colors.textPrimary,
    unfocusedLabelColor = colors.textPrimary,
    disabledLabelColor = colors.contendTertiary,
    errorLabelColor = colors.indicatorContendError,
    placeholderColor = colors.contendTertiary,
    disabledPlaceholderColor = colors.contendTertiary,
  )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun TextFieldPreview() {
  AppTheme(useDarkTheme = false) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
      PreviewRow(title = "Empty") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "",
          onValueChange = { },
          label = "Label",
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Filled") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "NotEditable") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          enabled = true,
          readOnly = true,
          onValueChange = { },
          trailingContent = {
            CloseIcon()
          },
          label = "Label",
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Disabled") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          enabled = false,
          onValueChange = { },
          trailingContent = {
            CloseIcon()
          },
          label = "Label",
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Filled Error") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          isError = true,
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Trailing") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          trailingContent = {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp),
            ) {
              Text(text = "Text")
              Spacer(modifier = Modifier.size(8.dp))
              CloseIcon()
            }
          },
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "TrailingIcon + Error") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          trailingContent = {
            CloseIcon()
          },
          placeholder = "Placeholder text",
          isError = true,
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "LeadingIcon") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          leadingContent = {
            CloseIcon()
          },
          placeholder = "Placeholder text",
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Leading + Error") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "Hello world",
          onValueChange = { },
          label = "Label",
          leadingContent = {
            CloseIcon()
          },
          placeholder = "Placeholder text",
          isError = true,
          helperText = "Helper text",
        )
      }
      PreviewRow(title = "Empty") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "",
          onValueChange = { },
          label = "Label",
        )
      }
      PreviewRow(title = "No label, no helper text") {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = "",
          onValueChange = { },
        )
      }
    }
  }
}
