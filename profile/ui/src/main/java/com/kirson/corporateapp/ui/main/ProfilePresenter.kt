package com.kirson.corporateapp.ui.main

import com.kirson.corporateapp.core.coroutine.BasePresenter
import com.kirson.corporateapp.profile.domain.ProfileModel
import com.kirson.corporateapp.routing.AppFlow
import com.kirson.corporateapp.ui.main.ProfileScreen.ViewIntents
import com.kirson.corporateapp.ui.main.ProfileScreen.ViewState
import ru.dimsuz.unicorn.coroutines.MachineDsl
import javax.inject.Inject

internal class ProfilePresenter @Inject constructor(
  private val profileModel: ProfileModel,
  private val coordinator: AppFlow.Coordinator,
) : BasePresenter<ViewState, ViewIntents, Unit>() {

  override fun MachineDsl<ViewState, Unit>.buildMachine() {
    initial = ViewState() to null

//    onEach(intent(ViewIntents::navigateOnBack)) {
//      action { _, _, _ ->
//        coordinator.handleEvent(AppFlow.Event.EnterPasswordDismissed)
//      }
//    }

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
}
