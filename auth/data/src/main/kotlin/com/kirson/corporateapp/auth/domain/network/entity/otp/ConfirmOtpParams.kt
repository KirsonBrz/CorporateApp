package com.kirson.corporateapp.auth.domain.network.entity.otp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ConfirmOtpParams(
  val phone: String,
  val otpId: String,
  val otpCode: String,
)
