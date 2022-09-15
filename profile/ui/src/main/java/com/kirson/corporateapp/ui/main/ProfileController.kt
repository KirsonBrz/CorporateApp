package com.kirson.corporateapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.kirson.corporateapp.profile.ui.R
import com.kirson.corporateapp.ui.core.uikit.KodeBankBaseController
import com.kirson.corporateapp.ui.core.uikit.component.*
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme
import com.kirson.corporateapp.ui.main.ProfileScreen.ViewIntents
import com.kirson.corporateapp.ui.main.ProfileScreen.ViewState

internal class ProfileController : KodeBankBaseController<ViewState, ViewIntents>() {

  override fun createConfig(): Config<ViewIntents> {
    return object : Config<ViewIntents> {
      override val intentsConstructor = ::ViewIntents
    }
  }

  override fun handleBack(): Boolean {
    intents.navigateOnBack()
    return true
  }

  @Composable
  override fun ScreenContent(state: ViewState) {
    Box(
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsWithImePadding(),
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        TopAppBar(
            modifier = Modifier.background(AppTheme.colors.backgroundPrimary),
            leftContent = {
              Text(
                  modifier = Modifier.padding(horizontal = 24.dp),
                  text = "Профиль",
                  style = AppTheme.typography.subtitle
              )
            },
            centerContent = {},
            rightContent = {}
        )
        HorizontalDivider()
        Box(
          modifier = Modifier
            .fillMaxSize()
            .weight(1f),
          contentAlignment = Alignment.Center,
        ) {
          Content()
        }
        OrdersBottomBar()
      }
      Snackbar(
        modifier = Modifier.align(Alignment.BottomCenter),
        message = state.errorMessage?.name(),
        onDismiss = intents.dismissError,
      )
    }
  }

  @Composable
  private fun Content(
    modifier: Modifier = Modifier,
  ) {

  }

  @Composable
  private fun OrdersBottomBar() {
    BottomBar(
      activeTab = MainSection.Profile,
      onTabClick = intents.switchSection,
    )
  }

  @Composable
  private fun Snackbar(
    modifier: Modifier = Modifier,
    message: String?,
    onDismiss: () -> Unit,
  ) {
    val snackbarHostState = remember { SnackbarHostState() }

    if (message != null) {
      LaunchedEffect(message) {
        snackbarHostState.showSnackbar(message = message)
        onDismiss()
      }
    }

    SnackbarHost(
      modifier = modifier,
      hostState = snackbarHostState,
      snackbar = { snackBarData ->
        ErrorSnackbar(Modifier.padding(16.dp), snackBarData.message)
      }
    )
  }
}

@Composable
private fun ProfileScreen.ErrorMessage.name(): String {
  return when (this) {
    ProfileScreen.ErrorMessage.LoginError -> stringResource(id = R.string.error_something_went_wrong_title)
  }
}

