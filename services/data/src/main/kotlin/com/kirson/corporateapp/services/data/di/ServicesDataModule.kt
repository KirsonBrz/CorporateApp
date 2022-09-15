package com.kirson.corporateapp.services.data.di

import com.kirson.corporateapp.services.domain.ServicesRepository
import com.kirson.corporateapp.services.data.ServicesRepositoryImpl
import com.kirson.corporateapp.services.data.network.ServiceApi
import toothpick.config.Module

class ServicesDataModule : Module() {
  init {
    bind(ServicesRepository::class.java)
      .to(ServicesRepositoryImpl::class.java)
      .singletonInScope()

    bind(ServiceApi::class.java)
      .toProvider(ServicesApiProvider::class.java)
      .providesSingletonInScope()
  }
}