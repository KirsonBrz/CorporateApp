package com.kirson.corporateapp.core.data.entity

data class OrderNM(
    val id: OrderIdNM,
    val departmentType: DepartmentNM.DepartmentTypeNM,
    val description: String
)