package com.kirson.corporateapp.profile.data.di

import com.kirson.corporateapp.profile.data.network.ProfileApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

internal class ProfileApiProvider @Inject constructor(
  private val retrofit: Retrofit,
) : Provider<ProfileApi> {
  override fun get(): ProfileApi {
    return retrofit.create(ProfileApi::class.java)
  }
}
