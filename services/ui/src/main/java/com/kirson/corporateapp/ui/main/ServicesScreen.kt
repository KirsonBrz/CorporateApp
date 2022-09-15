package com.kirson.corporateapp.ui.main

import androidx.compose.runtime.Immutable
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection

internal object ServicesScreen {
  class ViewIntents : BaseViewIntents() {
    val navigateOnBack = intent(name = "navigateOnBack")
    val switchSection = intent<MainSection>(name = "switchSection")
    val dismissError = intent(name = "dismissError")
  }

  @Immutable
  data class ViewState(
    val errorMessage: ErrorMessage? = null
  )

  sealed class ErrorMessage {
    object LoginError : ErrorMessage()
  }
}
