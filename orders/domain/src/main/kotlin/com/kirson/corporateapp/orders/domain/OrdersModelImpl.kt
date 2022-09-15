package com.kirson.corporateapp.orders.domain

import kotlinx.coroutines.CoroutineScope

import com.kirson.corporateapp.core.domain.BaseUseCase
import com.kirson.corporateapp.core.domain.entity.LceState
import javax.inject.Inject

internal class OrdersModelImpl @Inject constructor(
  scope: CoroutineScope,
  private val repository: OrdersRepository,
) : BaseUseCase<OrdersModelImpl.State>(scope, State()), OrdersModel {

  data class State(
    val userIdentificationState: LceState = LceState.None,
    val loginState: LceState = LceState.None
  )

}