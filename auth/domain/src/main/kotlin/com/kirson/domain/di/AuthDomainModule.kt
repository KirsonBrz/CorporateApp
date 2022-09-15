package com.kirson.corporateapp.auth.domain.di

import com.kirson.corporateapp.auth.domain.AuthUseCase
import com.kirson.corporateapp.auth.domain.AuthUseCaseImpl
import toothpick.config.Module

class AuthDomainModule : Module() {
  init {
    bind(AuthUseCase::class.java)
      .to(AuthUseCaseImpl::class.java)
      .singletonInScope()
  }
}