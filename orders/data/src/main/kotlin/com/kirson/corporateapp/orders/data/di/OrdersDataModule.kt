package com.kirson.corporateapp.orders.data.di

import com.kirson.corporateapp.orders.data.OrdersRepositoryImpl
import com.kirson.corporateapp.orders.data.network.OrdersApi
import com.kirson.corporateapp.orders.domain.OrdersRepository
import toothpick.config.Module

class OrdersDataModule : Module() {
  init {
    bind(OrdersRepository::class.java)
      .to(OrdersRepositoryImpl::class.java)
      .singletonInScope()

    bind(OrdersApi::class.java)
      .toProvider(OrdersApiProvider::class.java)
      .providesSingletonInScope()
  }
}