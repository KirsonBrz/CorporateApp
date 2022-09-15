package com.kirson.corporateapp.ui.mappers

import com.kirson.corporateapp.core.domain.entity.ServiceType
import com.kirson.corporateapp.ui.entity.ServiceItemId

internal fun ServiceItemId.toService(): ServiceType {
  return when(this){
    ServiceItemId(value = "1") -> ServiceType.Documents
    ServiceItemId(value = "2") -> ServiceType.CreateOrder
    else -> {
      ServiceType.CreateOrder
    }
  }
}