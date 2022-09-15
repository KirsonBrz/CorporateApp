package com.kirson.corporateapp.auth.domain.di

import com.kirson.corporateapp.auth.domain.AuthRepository
import com.kirson.corporateapp.auth.domain.AuthRepositoryImpl
import com.kirson.corporateapp.auth.domain.network.AuthApi
import toothpick.config.Module

class AuthDataModule : Module() {
  init {
    bind(AuthRepository::class.java)
      .to(AuthRepositoryImpl::class.java)
      .singletonInScope()

    bind(AuthApi::class.java)
      .toProvider(AuthApiProvider::class.java)
      .providesSingletonInScope()
  }
}