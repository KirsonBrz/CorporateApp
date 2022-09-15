package com.kirson.corporateapp.orders.data.di

import com.kirson.corporateapp.orders.data.network.OrdersApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

internal class OrdersApiProvider @Inject constructor(
  private val retrofit: Retrofit,
) : Provider<OrdersApi> {
  override fun get(): OrdersApi {
    return retrofit.create(OrdersApi::class.java)
  }
}
