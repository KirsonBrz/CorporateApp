package com.kirson.corporateapp.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirson.corporateapp.profile.ui.R
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun SectionCard(
        modifier: Modifier = Modifier,
        props: SectionCardProps,
        onClick: () -> Unit,
) {
  Surface(
    modifier = modifier.heightIn(min = 68.dp),
    shape = RoundedCornerShape(20.dp),
    color = AppTheme.colors.backgroundPrimary,
    elevation = 8.dp,
  ) {
    Row(
      modifier = Modifier
        .clickable(onClick = onClick)
        .padding(horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
        painter = painterResource(id = props.iconRes),
        contentDescription = "section icon"
      )
      Column(
        modifier = Modifier.padding(start = 12.dp)
      ) {
        Text(
          text = props.title,
          color = AppTheme.colors.textPrimary,
          style = AppTheme.typography.caption1,
        )
        if (props.subtitle != null) {
          Text(
            text = props.subtitle,
            color = AppTheme.colors.textSecondary,
            style = AppTheme.typography.caption2,
          )
        }
      }
      Spacer(modifier = Modifier.weight(weight = 1f))
      Icon(
        painter = painterResource(id = R.drawable.ic_edit_24),
        contentDescription = "chevron icon",
        tint = AppTheme.colors.textPrimary,
      )
    }
  }
}

@Immutable
data class SectionCardProps(
  val title: String,
  val subtitle: String? = null,
  @DrawableRes
  val iconRes: Int,
)

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun SectionCardPreview() {
  AppTheme {
    Column {
      SectionCard(
        modifier = Modifier.padding(horizontal = 16.dp),
        props = SectionCardProps(
          iconRes = R.drawable.ic_search_24,
          title = "Личные данные",
          subtitle = "ФИО, тариф, телефон, e-mail",
        ),
        onClick = {},
      )
      Spacer(modifier = Modifier.height(height = 24.dp))
      SectionCard(
        modifier = Modifier.padding(horizontal = 16.dp),
        props = SectionCardProps(
          iconRes = R.drawable.ic_search_24,
          title = "Настройки входа",
        ),
        onClick = {},
      )
      SectionCard(
        modifier = Modifier.padding(horizontal = 16.dp),
        props = SectionCardProps(
          iconRes = R.drawable.ic_search_24,
          title = "О приложении",
          subtitle = "Версия 1.0.0",
        ),
        onClick = {},
      )
    }
  }
}
