package com.kirson.corporateapp.ui.identification

import androidx.compose.runtime.Immutable
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.domain.entity.LceState

internal object UserIdentificationScreen {
  class ViewIntents : BaseViewIntents() {
    val changeText = intent<String>(name = "changeText")
    val login = intent(name = "login")
    val dismissError = intent(name = "dismissError")
  }

  @Immutable
  data class ViewState(
    val identificationLceState: LceState = LceState.None,
    val enteredPhoneNumber: String = "",
    val errorMessage: ErrorMessage? = null,
  )

  sealed class ErrorMessage {
    sealed class ValidationError : ErrorMessage() {
      object IncorrectPhoneNumber : ValidationError()
    }

    object IdentificationError : ErrorMessage()
  }
}