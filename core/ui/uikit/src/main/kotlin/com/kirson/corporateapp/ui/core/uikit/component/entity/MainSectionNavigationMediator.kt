package com.kirson.corporateapp.ui.core.uikit.component.entity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainSectionNavigationMediator {
  private val selectedSectionFlow = MutableStateFlow(MainSection.Services)

  fun requestSectionChange(section: MainSection) {
    selectedSectionFlow.value = section
  }

  val selectedSection: MainSection get() = selectedSectionFlow.value

  val selectedSectionChanges: Flow<MainSection> = selectedSectionFlow
}
