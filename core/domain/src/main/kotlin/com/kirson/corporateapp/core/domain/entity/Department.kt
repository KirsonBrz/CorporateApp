package com.kirson.corporateapp.core.domain.entity

data class Department(
    val id: DepartmentId,
    val type: DepartmentType
) {
    @JvmInline
    value class DepartmentId(val value: String)

    enum class DepartmentType {
        FinanceDepartment,
        ServiceDepartment,
        ManageDepartment
    }
}

