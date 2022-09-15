package com.kirson.corporateapp.services.data.di

import retrofit2.Retrofit
import com.kirson.corporateapp.services.data.network.ServiceApi
import javax.inject.Inject
import javax.inject.Provider

internal class ServicesApiProvider @Inject constructor(
  private val retrofit: Retrofit,
) : Provider<ServiceApi> {
  override fun get(): ServiceApi {
    return retrofit.create(ServiceApi::class.java)
  }
}
