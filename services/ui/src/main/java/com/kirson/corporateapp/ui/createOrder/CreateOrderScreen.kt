package com.kirson.corporateapp.ui.createOrder

import androidx.compose.runtime.Immutable
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.domain.entity.Department
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection

internal object CreateOrderScreen {
    class ViewIntents : BaseViewIntents() {
        val navigateOnBack = intent(name = "navigateOnBack")
        val changeDescription = intent<String>(name = "changeDescription")
        val selectDepartment = intent<Department.DepartmentId>(name = "selectDepartment")
        val createOrder = intent(name = "createOrder")
        val switchSection = intent<MainSection>(name = "switchSection")
        val dismissError = intent(name = "dismissError")
    }

    @Immutable
    data class ViewState(
        val departments: List<Department> = listOf(),
        val selectedDepartment: Department.DepartmentId? = null,
        val description: String = "",
        val errorMessage: ErrorMessage? = null
    )

    sealed class ErrorMessage {

        sealed class ValidationSuccess : ErrorMessage() {
            object SuccessCreateOrder : ValidationSuccess()
        }

        object LoginError : ErrorMessage()
    }

}
