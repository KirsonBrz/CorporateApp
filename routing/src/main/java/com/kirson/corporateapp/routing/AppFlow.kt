package com.kirson.corporateapp.routing

import com.kirson.corporateapp.core.routing.coordinator.FlowConfig
import com.kirson.corporateapp.core.routing.coordinator.FlowConstructor
import com.kirson.corporateapp.core.routing.coordinator.FlowCoordinator
import com.kirson.corporateapp.auth.domain.di.AuthDataModule
import com.kirson.corporateapp.auth.domain.di.AuthDomainModule
import com.kirson.corporateapp.orders.data.di.OrdersDataModule
import com.kirson.corporateapp.orders.domain.di.OrdersDomainModule
import com.kirson.corporateapp.profile.data.di.ProfileDataModule
import com.kirson.corporateapp.profile.domain.di.ProfileDomainModule
import com.kirson.corporateapp.services.data.di.ServicesDataModule
import com.kirson.corporateapp.services.domain.di.ServicesDomainModule
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection

interface AppFlow {
  companion object : FlowConstructor<Coordinator, Unit, Unit>(
      FlowConfig(
          flowId = "app_flow",
          flowModules = listOf(
              AppFlowModule(),
              AuthDataModule(),
              AuthDomainModule(),
              ServicesDataModule(),
              ServicesDomainModule(),
              OrdersDomainModule(),
              OrdersDataModule(),
              ProfileDataModule(),
              ProfileDomainModule()
              ),
          coordinatorClass = Coordinator::class.java
      )
  )

  interface Coordinator : FlowCoordinator<Event, Unit>

  sealed class Event {
    object LoginRequested : Event()
    object EnterPasswordDismissed : Event()
    object UserLoggedIn : Event()
    object ServicesRequested : Event()
    object ServicesDismissed : Event()
    data class Switch(val section: MainSection) : Event()
  }
}
