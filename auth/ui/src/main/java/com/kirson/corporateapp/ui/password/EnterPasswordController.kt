package com.kirson.corporateapp.ui.password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.kirson.corporateapp.auth.ui.R
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.ui.component.TextField
import com.kirson.corporateapp.ui.core.uikit.KodeBankBaseController
import com.kirson.corporateapp.ui.core.uikit.component.ErrorSnackbar
import com.kirson.corporateapp.ui.core.uikit.component.PrimaryButton
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ErrorMessage
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ViewIntents
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ViewState

internal class EnterPasswordController : KodeBankBaseController<ViewState, ViewIntents>() {

  override fun createConfig(): Config<ViewIntents> {
    return object : Config<ViewIntents> {
      override val intentsConstructor = ::ViewIntents
    }
  }

  override fun handleBack(): Boolean {
    intents.navigateOnBack()
    return true
  }

  @ExperimentalComposeUiApi
  @Composable
  override fun ScreenContent(state: ViewState) {
    Box(
      modifier = Modifier
        .statusBarsPadding()
        .navigationBarsWithImePadding(),
    ) {
      val keyboardController = LocalSoftwareKeyboardController.current
      Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Spacer(modifier = Modifier.height(56.dp))
        Image(
          painter = painterResource(id = R.drawable.img_kode_logo_90x40),
          contentDescription = "KODE logo"
        )
        Spacer(modifier = Modifier.weight(2f))
        val visualTransformation = if (state.isPasswordProtected) {
          PasswordVisualTransformation()
        } else {
          VisualTransformation.None
        }
        TextField(
          inputValue = state.enteredPassword,
          placeholder = stringResource(id = R.string.user_identification_password),
          onValueChange = intents.changeText,
          leadingIcon = {
            Icon(
              modifier = Modifier.padding(start = 16.dp),
              painter = painterResource(id = R.drawable.ic_lock_24),
              contentDescription = "phone icon",
              tint = AppTheme.colors.contendAccentPrimary
            )
          },
          trailingIcon = {
            PasswordFieldTrailingIcon(
              isLoginInProgress = state.loginLceState == LceState.Loading,
              isPasswordProtected = state.isPasswordProtected,
              onTogglePasswordVisibility = intents.togglePasswordVisibility,
            )
          },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          keyboardActions = KeyboardActions { keyboardController?.hide() },
          visualTransformation = visualTransformation
        )
        Spacer(modifier = Modifier.weight(weight = 5f))
        PrimaryButton(
          modifier = Modifier.fillMaxWidth(),
          text = stringResource(id = R.string.user_identification_enter),
          onClick = {
            keyboardController?.hide()
            intents.login()
          },
          enabled = state.loginLceState != LceState.Loading
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
      }
      Snackbar(
        modifier = Modifier.align(Alignment.BottomCenter),
        message = state.errorMessage?.name(),
        onDismiss = intents.dismissError,
      )
    }
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

  @Composable
  private fun PasswordFieldTrailingIcon(
    isLoginInProgress: Boolean,
    isPasswordProtected: Boolean,
    onTogglePasswordVisibility: () -> Unit
  ) {
    if (isLoginInProgress) {
      CircularProgressIndicator(
        modifier = Modifier
          .padding(end = 16.dp)
          .size(20.dp),
        color = AppTheme.colors.contendAccentPrimary,
        strokeWidth = 1.5.dp
      )
    } else {
      IconButton(
        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
        onClick = onTogglePasswordVisibility,
      ) {
        Icon(
          painter = painterResource(
            id = if (isPasswordProtected) R.drawable.ic_crossed_eye_24 else R.drawable.ic_eye_24
          ),
          contentDescription = "phone icon",
          tint = AppTheme.colors.textTertiary
        )
      }
    }
  }
}

@Composable
private fun ErrorMessage.name(): String {
  return when(this) {
    ErrorMessage.LoginError -> stringResource(id = R.string.error_something_went_wrong_title)
  }
}
