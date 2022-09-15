package com.kirson.corporateapp.ui.createOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.kirson.corporateapp.services.ui.R
import com.kirson.corporateapp.ui.component.DepartmentItem
import com.kirson.corporateapp.ui.core.uikit.KodeBankBaseController
import com.kirson.corporateapp.ui.core.uikit.component.*
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme
import com.kirson.corporateapp.ui.createOrder.CreateOrderScreen.ViewIntents
import com.kirson.corporateapp.ui.createOrder.CreateOrderScreen.ViewState
import com.mapswithme.uikit.component.TextArea

internal class CreateOrderController : KodeBankBaseController<ViewState, ViewIntents>() {

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
        val focusManager = LocalFocusManager.current
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
                    title = "Заявка №203",
                    onNavigationIconClick = {
                        intents.navigateOnBack()
                        focusManager.clearFocus()
                    },
                    navigationIcon = NavigationIcon.ArrowBack
                )

                HorizontalDivider()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    ContentMain(
                        state = state,
                    )
                }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            onClick = intents.createOrder,
            text = "Проблема решена"
        )
            }
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = state.errorMessage?.name(),
                onDismiss = intents.dismissError,
            )
        }
    }

    @Composable
    private fun ContentMain(
        modifier: Modifier = Modifier,
        state: ViewState,
    ) {
        val focusManager = LocalFocusManager.current
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f),
                    text = "Создана",
                    style = AppTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    color = AppTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    modifier = Modifier.weight(0.6f),
                    text = "В процессе ⌚",
                    style = AppTheme.typography.body3,
                    textAlign = TextAlign.Start,
                    color = Color.DarkGray
                )
            }



            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "9 июн 2:42",
                style = AppTheme.typography.body4,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Тема заявки",
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Microsoft Word запрашивает лицензионный ключ",
                style = AppTheme.typography.bodySemibold,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Назначенный специалист",
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Иванов И. А.",
                style = AppTheme.typography.bodySemibold,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Рассматривается",
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Отдел тех. обслуживания",
                style = AppTheme.typography.bodySemibold,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Ответ специалиста:",
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.textPrimary
            )
            Text(
                modifier = Modifier.padding(vertical = 1.dp),
                text = "Необходимо ввести новый лицензионный ключ, напишите свой почтовый ящик",
                style = AppTheme.typography.bodyRegular,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(25.dp))

//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(16.dp),
//            elevation = 8.dp,
//            backgroundColor = AppTheme.colors.backgroundPrimary
//        ) {
//            LazyColumn(
//                modifier = modifier,
//                contentPadding = PaddingValues(8.dp)
//            )
//            {
//                items(state.departments) { department ->
//                    DepartmentItem(
//                        department = department.type,
//                        selected = department.id == state.selectedDepartment,
//                        onDepartmentClick = { intents.selectDepartment(department.id) }
//                    )
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextArea(
                    modifier = Modifier.weight(0.75f),
                    label = "Обратная связь",
                    value = state.description,
                    onValueChange = intents.changeDescription,
                    placeholder = "Ответ специалисту",
                    helperText = "Необязательный",
                    isError = false,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    maxLines = 120,
                    counterMaxLength = 400,
                    counterEnabled = true
                )
                PrimaryButton(
                    modifier = Modifier
                        .width(100.dp)
                        .weight(0.25f)
                        .align(Alignment.CenterVertically)
                        .padding(all = 8.dp),
                    onClick = {},
                    text = "→",

                    )
            }
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
}

@Composable
private fun CreateOrderScreen.ErrorMessage.name(): String {
    return when (this) {
        CreateOrderScreen.ErrorMessage.ValidationSuccess.SuccessCreateOrder -> stringResource(R.string.success_title)
        CreateOrderScreen.ErrorMessage.LoginError -> stringResource(id = R.string.error_something_went_wrong_title)
    }
}
