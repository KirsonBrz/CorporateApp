package com.kirson.corporateapp.core.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import timber.log.Timber

internal class AttachAccessTokenInterceptor(
  private val authTokenPersistence: TokensPersistence
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val newRequest = chain.request().addAccessToken(authTokenPersistence)
    return chain.proceed(newRequest)
  }
}

internal fun Request.addAccessToken(authPersistence: TokensPersistence): Request {
  val accessToken = authPersistence.accessToken
  return this
    .newBuilder()
    .apply {
      if (accessToken != null) {
        removeHeader(ACCESS_TOKEN_HEADER)
        addHeader(ACCESS_TOKEN_HEADER, accessToken.value)
      } else Timber.d("access token is empty, nothing to attach")
    }
    .build()
}

internal const val ACCESS_TOKEN_HEADER = "Authorization"