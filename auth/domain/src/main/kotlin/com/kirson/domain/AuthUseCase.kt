package com.kirson.corporateapp.auth.domain

import kotlinx.coroutines.flow.Flow
import com.kirson.corporateapp.core.domain.entity.LceState

interface AuthUseCase {
  suspend fun identifyUser(phoneNumber: String)
  val userIdentificationState: Flow<LceState>

  suspend fun login(password: String)
  val loginState: Flow<LceState>
}