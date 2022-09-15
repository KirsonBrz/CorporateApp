package com.kirson.corporateapp.core.data.entity

data class AuthTokens(
  val accessToken: AccessToken,
  val refreshToken: RefreshToken,
)