package com.kirson.corporateapp.auth.domain

import com.kirson.corporateapp.auth.domain.AuthRepository
import com.kirson.corporateapp.auth.domain.network.AuthApi
import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
  private val api: AuthApi,
  private val authPersistence: TokensPersistence,
) : AuthRepository {
  override suspend fun identifyUser(phoneNumber: String) {

  }

  override suspend fun login(password: String) {

  }

  override suspend fun createAccount(login: String, password: String) {
  }
}
