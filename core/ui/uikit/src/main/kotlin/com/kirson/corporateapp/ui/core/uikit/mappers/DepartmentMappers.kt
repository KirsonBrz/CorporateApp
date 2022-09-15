package com.kirson.corporateapp.ui.core.uikit.mappers

import com.kirson.corporateapp.core.domain.entity.Department

fun Department.DepartmentType.toDepartmentType(): String{
  return when(this){
    Department.DepartmentType.FinanceDepartment -> "Отдел финансов"
    Department.DepartmentType.ServiceDepartment -> "Отдел тех. обслуживания"
    Department.DepartmentType.ManageDepartment -> "Отдел управления"
    else -> "Отдел финансов"
  }
}