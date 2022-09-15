package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

/**
 * [VisualTransformation] implementation for changing visual output of the input field.
 *
 * This implementation can be used for changing visual output of the text in the input field.
 * It accept a [mask] and [replacement] symbol for visual formatting and uses [defaultText]
 * when received in [filter] callback text is empty or blank.
 *
 * It uses [MaskOffsetMapping] class that can map offset to the original or transformed text
 * according to [replacement] symbol.
 *
 * @param mask The pattern used to change visual of received text
 * @param replacement The symbol to be replaced in the [mask] from the received text
 *
 * @see VisualTransformation
 * @see MaskOffsetMapping
 * @see OffsetMapping
 */
class MaskVisualTransformation(
  private val mask: String,
  private val replacement: Char = '*',
) : VisualTransformation {

  private val offsetMapper = MaskOffsetMapping(mask = mask, replacement = replacement)

  override fun filter(text: AnnotatedString): TransformedText {
    return TransformedText(
      text = AnnotatedString(
        text = applyMask(
          text = text.text,
          mask = mask,
          replacement = replacement
        ),
      ),
      offsetMapping = offsetMapper,
    )
  }

  private fun applyMask(text: String, mask: String, replacement: Char = '*'): String {
    if (text.isBlank() || mask.isBlank())
      return ""

    var count = 0
    var result = ""

    var index = 0
    while (count < text.length && index < mask.length) {
      result += when (val maskChar = mask[index]) {
        replacement -> {
          count++
          text[count - 1]
        }
        else -> maskChar
      }

      index++
    }

    return result
  }
}

/**
 * [OffsetMapping] used to calculate new offset of caret in the text field according to [mask]
 *
 * @param mask The mask of the input field's text that's offset should be calculated according to offset
 * @param replacement The replacement symbol in the mask
 *
 * @see OffsetMapping
 */
private class MaskOffsetMapping(
  private val mask: String,
  private val replacement: Char = '*'
) : OffsetMapping {

  override fun originalToTransformed(offset: Int): Int {
    if (offset == 0) return 0

    var replacementsCount = 0
    var fixedOffset = 0

    for (char in mask) {
      if (char == replacement) replacementsCount++
      fixedOffset++
      if (replacementsCount == offset) break
    }

    return fixedOffset
  }

  override fun transformedToOriginal(offset: Int): Int {
    if (offset == 0) return mask.indexOfFirst { it == replacement }

    var skippedCount = 0
    var count = 0

    for (char in mask) {
      if (char != replacement) skippedCount++
      count++
      if (count == offset) break
    }

    return offset - skippedCount
  }
}

@Preview
@Composable
private fun MaskVisualTransformationPreview() {
  AppTheme {
    Column(
      modifier = Modifier
        .background(AppTheme.colors.backgroundPrimary)
        .padding(12.dp)
    ) {
      val transformation = MaskVisualTransformation(mask = "+123 *** *** ***")

      Text(text = transformation.filter(AnnotatedString(text = "")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "12")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "123")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "123123")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "1231232")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "123123123")).text)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = transformation.filter(AnnotatedString(text = "1231231212")).text)
    }
  }
}
