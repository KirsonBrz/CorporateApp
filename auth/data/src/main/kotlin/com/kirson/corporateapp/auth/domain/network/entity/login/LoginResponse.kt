package com.kirson.corporateapp.auth.domain.network.entity.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LoginResponse(
  val accessToken: String,
  val refreshToken: String,
)