package com.kirson.corporateapp.routing

import com.kirson.corporateapp.ui.core.uikit.component.entity.MainSectionNavigationMediator
import toothpick.config.Module

/**
 * Содержит "внутренние" зависимости данного flow, для использования только внутри этого gradle-модуля.
 */
internal class AppFlowModule : Module() {
  init {
    bind(AppFlow.Coordinator::class.java).to(AppFlowCoordinator::class.java).singletonInScope()
    bind(MainSectionNavigationMediator::class.java).toInstance(MainSectionNavigationMediator())
  }
}
