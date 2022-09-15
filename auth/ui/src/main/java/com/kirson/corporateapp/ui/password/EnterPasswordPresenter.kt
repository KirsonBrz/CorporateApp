package com.kirson.corporateapp.ui.password

import ru.dimsuz.unicorn.coroutines.MachineDsl
import com.kirson.corporateapp.core.coroutine.BasePresenter
import com.kirson.corporateapp.auth.domain.AuthUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.routing.AppFlow
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ErrorMessage
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ViewIntents
import com.kirson.corporateapp.ui.password.EnterPasswordScreen.ViewState
import javax.inject.Inject

internal class EnterPasswordPresenter @Inject constructor(
  private val authUseCase: AuthUseCase,
  private val coordinator: AppFlow.Coordinator,
) : BasePresenter<ViewState, ViewIntents, Unit>() {

  override fun MachineDsl<ViewState, Unit>.buildMachine() {
    initial = ViewState() to null

    onEach(intent(ViewIntents::navigateOnBack)) {
      action { _, _, _ ->
        coordinator.handleEvent(AppFlow.Event.EnterPasswordDismissed)
      }
    }

    onEach(intent(ViewIntents::togglePasswordVisibility)) {
      transitionTo { state, _ ->
        state.copy(
          isPasswordProtected = !state.isPasswordProtected
        )
      }
    }

    onEach(intent(ViewIntents::changeText)) {
      transitionTo { state, text ->
        state.copy(enteredPassword = text)
      }
    }

    onEach(intent(ViewIntents::login)) {
      action { _, newState, _ ->
        executeAsync {
          authUseCase.login(newState.enteredPassword)
        }
      }
    }

    onEach(intent(ViewIntents::dismissError)) {
      transitionTo { state, _ ->
        state.copy(errorMessage = null)
      }
    }

    onEach(authUseCase.loginState) {
      transitionTo { state, lceState ->
        state.copy(
          loginLceState = lceState,
          errorMessage = if(lceState is LceState.Error) ErrorMessage.LoginError else state.errorMessage
        )
      }

      action { _, newState, _ ->
        if (newState.loginLceState == LceState.Content) {
          coordinator.handleEvent(AppFlow.Event.UserLoggedIn)
        }
      }
    }
  }
}
