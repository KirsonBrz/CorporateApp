package com.kirson.corporateapp.orders.data

import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import com.kirson.corporateapp.orders.data.network.OrdersApi
import com.kirson.corporateapp.orders.domain.OrdersRepository
import javax.inject.Inject

internal class OrdersRepositoryImpl @Inject constructor(
  private val api: OrdersApi,
  private val authPersistence: TokensPersistence,
) : OrdersRepository {

}
