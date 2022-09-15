package com.kirson.corporateapp.auth.domain.network

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import com.kirson.corporateapp.auth.domain.network.entity.login.LoginParams
import com.kirson.corporateapp.auth.domain.network.entity.login.LoginResponse
import com.kirson.corporateapp.auth.domain.network.entity.otp.ConfirmOtpParams
import com.kirson.corporateapp.auth.domain.network.entity.otp.ConfirmOtpResponse
import com.kirson.corporateapp.auth.domain.network.entity.otp.RequestOtpParams
import com.kirson.corporateapp.auth.domain.network.entity.otp.RequestOtpResponse

internal interface AuthApi {
  @POST("api/auth/login")
  suspend fun requestOtp(
    @Query("__code") code: Int = 200,
    @Body params: RequestOtpParams,
  ): RequestOtpResponse

  @POST("api/auth/confirm")
  suspend fun confirmOtp(
    @Body params: ConfirmOtpParams,
  ): ConfirmOtpResponse

  @POST("api/auth/enter")
  suspend fun login(
    @Query("__code") code: Int = 200,
    @Body params: LoginParams,
  ): LoginResponse
}