package com.kirson.corporateapp.services.domain.di

import com.kirson.corporateapp.services.domain.ServicesModel
import com.kirson.corporateapp.services.domain.ServicesModelImpl
import toothpick.config.Module

class ServicesDomainModule : Module() {
  init {
    bind(ServicesModel::class.java)
      .to(ServicesModelImpl::class.java)
      .singletonInScope()
  }
}