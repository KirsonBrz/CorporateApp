package com.kirson.corporateapp.auth.domain.network.entity.otp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestOtpResponse(
  val otpId: String,
  val otpLen: String,
  val otpCode: String,
)
