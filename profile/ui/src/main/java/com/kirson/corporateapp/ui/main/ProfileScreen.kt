package com.kirson.corporateapp.ui.main

import androidx.compose.runtime.Immutable
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection

internal object ProfileScreen {
  class ViewIntents : BaseViewIntents() {
    val navigateOnBack = intent(name = "navigateOnBack")
    val dismissError = intent(name = "dismissError")
    val switchSection = intent<MainSection>(name = "switchSection")
  }

  @Immutable
  data class ViewState(

    val errorMessage: ErrorMessage? = null
  )

  sealed class ErrorMessage {
    object LoginError : ErrorMessage()
  }
}
