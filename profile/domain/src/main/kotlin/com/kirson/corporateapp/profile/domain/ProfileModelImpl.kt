package com.kirson.corporateapp.profile.domain

import kotlinx.coroutines.CoroutineScope

import com.kirson.corporateapp.core.domain.BaseUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import javax.inject.Inject

internal class ProfileModelImpl @Inject constructor(
  scope: CoroutineScope,
  private val repository: ProfileRepository,
) : BaseUseCase<ProfileModelImpl.State>(scope, State()), ProfileModel {

  data class State(
    val userIdentificationState: LceState = LceState.None,
    val loginState: LceState = LceState.None
  )

}