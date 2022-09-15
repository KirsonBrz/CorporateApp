package com.kirson.corporateapp.orders.domain.di

import com.kirson.corporateapp.orders.domain.OrdersModel
import com.kirson.corporateapp.orders.domain.OrdersModelImpl
import toothpick.config.Module

class OrdersDomainModule : Module() {
  init {
    bind(OrdersModel::class.java)
      .to(OrdersModelImpl::class.java)
      .singletonInScope()
  }
}