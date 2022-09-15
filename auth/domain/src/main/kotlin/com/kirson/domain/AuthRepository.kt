package com.kirson.corporateapp.auth.domain

interface AuthRepository {
  suspend fun identifyUser(phoneNumber: String)
  suspend fun login(password: String)
  suspend fun createAccount(login: String, password: String)
}
