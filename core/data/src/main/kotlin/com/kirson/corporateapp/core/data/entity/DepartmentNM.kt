package com.kirson.corporateapp.core.data.entity

data class DepartmentNM(
    val id: DepartmentIdNM,
    val type: DepartmentTypeNM
){
  @JvmInline
  value class DepartmentIdNM (val value: String)

  enum class DepartmentTypeNM{
    FinanceDepartment,
    ServiceDepartment
  }
}

