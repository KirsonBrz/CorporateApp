package com.kirson.corporateapp.profile.data.di

import com.kirson.corporateapp.profile.data.ProfileRepositoryImpl
import com.kirson.corporateapp.profile.data.network.ProfileApi
import com.kirson.corporateapp.profile.domain.ProfileRepository
import toothpick.config.Module

class ProfileDataModule : Module() {
  init {
    bind(ProfileRepository::class.java)
      .to(ProfileRepositoryImpl::class.java)
      .singletonInScope()

    bind(ProfileApi::class.java)
      .toProvider(ProfileApiProvider::class.java)
      .providesSingletonInScope()
  }
}