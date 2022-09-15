package com.kirson.corporateapp.ui.identification

import ru.dimsuz.unicorn.coroutines.MachineDsl
import com.kirson.corporateapp.ui.identification.UserIdentificationScreen.ViewState
import com.kirson.corporateapp.ui.identification.UserIdentificationScreen.ViewIntents
import com.kirson.corporateapp.core.coroutine.BasePresenter
import com.kirson.corporateapp.auth.domain.AuthUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.routing.AppFlow
import com.kirson.corporateapp.ui.identification.UserIdentificationScreen.ErrorMessage
import javax.inject.Inject

internal class UserIdentificationPresenter @Inject constructor(
  private val authUseModel: AuthUseCase,
  private val coordinator: AppFlow.Coordinator,
) : BasePresenter<ViewState, ViewIntents, Unit>() {

  override fun MachineDsl<ViewState, Unit>.buildMachine() {
    initial = ViewState() to null

    onEach(intent(ViewIntents::changeText)) {
      transitionTo { state, text ->
        state.copy(enteredPhoneNumber = text.take(REQUIRED_PHONE_LENGTH))
      }
    }

    onEach(intent(ViewIntents::dismissError)) {
      transitionTo { state, _ ->
        val identificationLceState = if (state.identificationLceState is LceState.Error) {
          LceState.None
        } else {
          state.identificationLceState
        }
        state.copy(
          identificationLceState = identificationLceState,
          errorMessage = null,
        )
      }
    }

    onEach(intent(ViewIntents::login)) {
//      transitionTo { state, _ ->
//        val validationError = if (state.enteredPhoneNumber.length != REQUIRED_PHONE_LENGTH) {
//          ErrorMessage.ValidationError.IncorrectPhoneNumber
//        } else {
//          state.errorMessage
//        }
//        state.copy(
//          errorMessage = validationError,
//        )
//      }
      action { _, _, _ ->
        coordinator.handleEvent(AppFlow.Event.ServicesRequested)
      }
    }

//    onEach(authUseModel.userIdentificationState) {
//      transitionTo { state, lceState ->
//        state.copy(
//          identificationLceState = lceState,
//          errorMessage = if(lceState is LceState.Error) ErrorMessage.IdentificationError else state.errorMessage
//        )
//      }
//      action { _, newState, _ ->
//        if (newState.identificationLceState == LceState.Content) {
//          coordinator.handleEvent(AppFlow.Event.LoginRequested)
//        }
//      }
//    }
  }
}

private const val REQUIRED_PHONE_LENGTH = 10
