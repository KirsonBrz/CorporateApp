package com.kirson.corporateapp.routing

import com.kirson.corporateapp.core.routing.Route
import com.kirson.corporateapp.core.routing.Router
import com.kirson.corporateapp.core.routing.coordinator.BaseFlowCoordinator
import com.kirson.corporateapp.core.routing.coordinator.entity.LifecycleCommand
import com.kirson.corporateapp.core.routing.transition.RouterTransitionType
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSection
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSectionFlowResult
import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSectionNavigationMediator
import toothpick.Scope
import javax.inject.Inject

internal class AppFlowCoordinator @Inject constructor(
  scope: Scope,
  private val router: Router,
  private val sectionMediator: MainSectionNavigationMediator,
) : BaseFlowCoordinator<AppFlow.Event, Unit>(scope), AppFlow.Coordinator {

  override fun openInitialRoute(beforePushClearUntil: Route?) {
    return router.push(
      AppRoute.Login.UserIdentificationKey,
      createContext(RouterTransitionType.None)
    )
  }


  private fun createFlow(section: MainSection) {
    return when (section) {
      MainSection.Services -> {
        router.push(AppRoute.Services.ServicesKey,  createContext(RouterTransitionType.Fade))
      }
      MainSection.Orders -> {
        router.push(AppRoute.Orders.OrdersKey,  createContext(RouterTransitionType.Fade))
      }
      MainSection.Profile -> {
        router.push(AppRoute.Profile.ProfileKey,  createContext(RouterTransitionType.Fade))
      }
    }
  }


  override fun handleEvent(event: AppFlow.Event) {
    when (event) {
      AppFlow.Event.LoginRequested -> router.push(AppRoute.Login.EnterPassword, createContext())
      AppFlow.Event.EnterPasswordDismissed -> router.pop()
      AppFlow.Event.UserLoggedIn -> router.push(AppRoute.Services.ServicesKey, createContext())
      AppFlow.Event.ServicesDismissed -> router.pop()
      AppFlow.Event.ServicesRequested -> router.push(AppRoute.Services.ServicesKey, createContext())
      is AppFlow.Event.Switch -> createFlow(event.section)
    }
  }


}
