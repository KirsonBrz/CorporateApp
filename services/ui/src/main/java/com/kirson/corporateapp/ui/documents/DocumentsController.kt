package com.kirson.corporateapp.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.kirson.corporateapp.services.ui.R
import com.kirson.corporateapp.ui.component.DocumentTemplateComponent
import com.kirson.corporateapp.ui.core.uikit.KodeBankBaseController
import com.kirson.corporateapp.ui.core.uikit.component.*
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme
import com.kirson.corporateapp.ui.documents.DocumentsScreen.ErrorMessage
import com.kirson.corporateapp.ui.documents.DocumentsScreen.ViewIntents
import com.kirson.corporateapp.ui.documents.DocumentsScreen.ViewState
import com.kirson.corporateapp.ui.documents.entity.Document

internal class DocumentsController : KodeBankBaseController<ViewState, ViewIntents>() {

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
            title = "Документация",
            onNavigationIconClick = intents.navigateOnBack,
            navigationIcon = NavigationIcon.ArrowBack
        )
        HorizontalDivider()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
          Column(
              modifier = Modifier
          ) {
            Documents(
                documents = state.documents
            )
          }

        }
        ServicesBottomBar()
      }
      Snackbar(
          modifier = Modifier.align(Alignment.BottomCenter),
          message = state.errorMessage?.name(),
          onDismiss = intents.dismissError,
      )
    }
  }

  @Composable
  private fun Documents(
      modifier: Modifier = Modifier,
      documents: List<Document>
  ) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    )
    {
      items(documents) { document ->
        DocumentTemplateComponent(
            modifier = modifier.fillMaxWidth(),
            title = document.title,
            description = document.description,
            onDocumentClick = {},
            onDownloadClick = {}
        )
      }
    }
  }

  @Composable
  private fun ServicesBottomBar() {
    BottomBar(
        activeTab = MainSection.Services,
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
private fun ErrorMessage.name(): String {
  return when (this) {
    ErrorMessage.LoginError -> stringResource(id = R.string.error_something_went_wrong_title)
  }
}
