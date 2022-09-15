package com.kirson.corporateapp.ui.main.entity

import android.graphics.Color
import com.kirson.corporateapp.ui.entity.ServiceItemId

data class ServiceItem(
    val id: ServiceItemId,
    val icon: Int,
    val name: String,
    val background: Int,
) {

}