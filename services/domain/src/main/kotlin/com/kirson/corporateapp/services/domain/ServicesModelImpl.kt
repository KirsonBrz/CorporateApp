package com.kirson.corporateapp.services.domain

import kotlinx.coroutines.CoroutineScope

import com.kirson.corporateapp.core.domain.BaseUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import com.kirson.corporateapp.core.routing.coordinator.BaseFlowCoordinator
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSectionNavigationMediator
import javax.inject.Inject

internal class ServicesModelImpl @Inject constructor(
  scope: CoroutineScope,
  private val repository: ServicesRepository,
) : BaseUseCase<ServicesModelImpl.State>(scope, State()), ServicesModel {

  data class State(
    val userIdentificationState: LceState = LceState.None,
    val loginState: LceState = LceState.None
  )

}