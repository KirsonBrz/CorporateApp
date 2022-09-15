package com.kirson.corporateapp.auth.domain.network.entity.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LoginParams(
  val guestToken: String,
  val password: String,
)
