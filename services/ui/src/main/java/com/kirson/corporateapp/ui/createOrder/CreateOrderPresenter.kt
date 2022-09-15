package com.kirson.corporateapp.ui.createOrder

import com.kirson.corporateapp.core.coroutine.BasePresenter
import com.kirson.corporateapp.core.domain.entity.Department
import com.kirson.corporateapp.routing.AppFlow
import com.kirson.corporateapp.services.domain.ServicesModel
import com.kirson.corporateapp.ui.createOrder.CreateOrderScreen.ViewIntents
import com.kirson.corporateapp.ui.createOrder.CreateOrderScreen.ViewState
import ru.dimsuz.unicorn.coroutines.MachineDsl
import javax.inject.Inject

internal class CreateOrderPresenter @Inject constructor(
    private val servicesModel: ServicesModel,
    private val coordinator: AppFlow.Coordinator,
) : BasePresenter<ViewState, ViewIntents, Unit>() {

  override fun MachineDsl<ViewState, Unit>.buildMachine() {
    initial = ViewState(
        departments = listOf(
            Department(
                id = Department.DepartmentId("1"),
                type = Department.DepartmentType.FinanceDepartment
            ),
            Department(
                id = Department.DepartmentId("2"),
                type = Department.DepartmentType.ServiceDepartment
            ),
            Department(
                id = Department.DepartmentId("3"),
                type = Department.DepartmentType.ManageDepartment
            ),
        )
    ) to null

    onEach(intent(ViewIntents::navigateOnBack)) {
      action { _, _, _ ->
        coordinator.handleEvent(AppFlow.Event.ServiceInfoDismissed)
      }
    }

    onEach(intent(ViewIntents::changeDescription)) {
      transitionTo { state, description ->
        state.copy(description = description.take(400))
      }
    }

    onEach(intent(ViewIntents::selectDepartment)) {
      transitionTo { state, departmentId ->
        state.copy(
            selectedDepartment = departmentId
        )
      }
    }

    onEach(intent(ViewIntents::createOrder)) {
        transitionTo { state, _ ->
            val validationError = if (state.selectedDepartment != null) {
                CreateOrderScreen.ErrorMessage.ValidationSuccess.SuccessCreateOrder
            } else {
                state.errorMessage
            }
            state.copy(
                errorMessage = validationError,
            )
        }

      action { state, _, _ ->
        if (state.selectedDepartment != null) {
          executeAsync {
//            servicesModel.createOrder(
//                order = Order(
//                    id = state.selectedDepartment,
//                    departmentType = state.departments.find { it.type }.type,
//                    description = state.description,
//                )
//            )
          }
        }
          coordinator.handleEvent(AppFlow.Event.ServiceInfoDismissed)
      }
    }

    onEach(intent(ViewIntents::switchSection)) {
      action { _, _, section ->
        coordinator.handleEvent(AppFlow.Event.Switch(section))
      }
    }

    onEach(intent(ViewIntents::dismissError)) {
      transitionTo { state, _ ->
        state.copy(errorMessage = null)
      }
    }
  }

//  private fun createInitialState(): ViewState {
//    return ViewState(
//        description = "",
//        order = Order(
//            description = "jhkjnnf",
//            department = Department.DepartmentType.FinanceDepartment,
//            id = Department.DepartmentId("1")
//        ),
//        departments = listOf(
//            Department.DepartmentType.ServiceDepartment,
//            Department.DepartmentType.FinanceDepartment
//        )
//    )
//  }
}
