package com.kirson.corporateapp.ui.core.uikit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.kirson.corporateapp.core.ui.uikit.R
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

@Composable
fun BottomBar(
  modifier: Modifier = Modifier,
  activeTab: MainSection,
  onTabClick: (MainSection) -> Unit,
) {
  Column(
    modifier = modifier
  ) {
    HorizontalDivider()
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.backgroundPrimary),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      val count = MainSection.values().size
      MainSection.values().forEach { section ->
        BottomBarTab(
          modifier = Modifier
            .heightIn(min = 56.dp)
            .weight(1f / count)
            .clickable { onTabClick(section) },
          activeIconResId = section.iconResId,
          titleResId = section.titleResId,
          isActive = section == activeTab
        )
      }
    }
  }
}

@Composable
private fun BottomBarTab(
  modifier: Modifier,
  activeIconResId: Int,
  titleResId: Int,
  isActive: Boolean,
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Icon(
      painter = painterResource(id = activeIconResId),
      contentDescription = "bottom bar icon",
      tint = if (isActive) AppTheme.colors.textPrimary else AppTheme.colors.textTertiary,
    )
    Text(
      modifier = Modifier
        .padding(top = 2.dp)
        .heightIn(min = 16.dp),
      style = AppTheme.typography.caption1,
      text = stringResource(id = titleResId),
      color = if (isActive) AppTheme.colors.textPrimary else AppTheme.colors.textTertiary,
    )
  }
}

private val MainSection.iconResId: Int
  get() {
    return when (this) {
      MainSection.Services -> R.drawable.ic_more_24
      MainSection.Profile -> R.drawable.ic_user_24
      MainSection.Orders -> R.drawable.ic_check_24
    }
  }

private val MainSection.titleResId: Int
  get() {
    return when (this) {
      MainSection.Services -> R.string.services_section
      MainSection.Orders -> R.string.orders_section
      MainSection.Profile -> R.string.profile_section
    }
  }

@Preview(showBackground = true, widthDp = 360)
@Composable
internal fun BottomBarPreview() {
  AppTheme {
    BottomBar(
      activeTab = MainSection.Services,
      onTabClick = { },
    )
  }
}
