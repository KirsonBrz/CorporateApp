package com.kirson.corporateapp.ui.documents

import androidx.compose.runtime.Immutable
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.documents.entity.Document

internal object DocumentsScreen {
  class ViewIntents : BaseViewIntents() {
    val navigateOnBack = intent(name = "navigateOnBack")
    val onItemClick = intent<String>(name = "onItemClick")
    val switchSection = intent<MainSection>(name = "switchSection")
    val dismissError = intent(name = "dismissError")
  }

  @Immutable
  data class ViewState(
      val documents: List<Document> = emptyList(),
      val errorMessage: ErrorMessage? = null
  )

  sealed class ErrorMessage {
    object LoginError : ErrorMessage()
  }
}
