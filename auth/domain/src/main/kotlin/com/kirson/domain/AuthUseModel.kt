package com.kirson.corporateapp.auth.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

import com.kirson.corporateapp.core.domain.BaseUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import javax.inject.Inject

internal class AuthUseCaseImpl @Inject constructor(
  scope: CoroutineScope,
  private val repository: AuthRepository,
) : BaseUseCase<AuthUseCaseImpl.State>(scope, State()), AuthUseCase  {

  data class State(
    val userIdentificationState: LceState = LceState.None,
    val loginState: LceState = LceState.None
  )

  override suspend fun identifyUser(phoneNumber: String) {
    setState { copy(userIdentificationState = LceState.Loading) }
    try {
      repository.identifyUser(phoneNumber)
      setState { copy(userIdentificationState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(userIdentificationState = LceState.Error(e.message)) }
    }
  }

  override val userIdentificationState: Flow<LceState>
    get() = stateFlow.map { it.userIdentificationState }.distinctUntilChanged()

  override suspend fun login(password: String) {
    setState { copy(loginState = LceState.Loading) }
    try {
      repository.login(password)
      setState { copy(loginState = LceState.Content) }
    } catch (e: Exception) {
      setState { copy(loginState = LceState.Error(e.message)) }
    }
  }

  override val loginState: Flow<LceState>
    get() = stateFlow.map { it.loginState }.distinctUntilChanged()
}