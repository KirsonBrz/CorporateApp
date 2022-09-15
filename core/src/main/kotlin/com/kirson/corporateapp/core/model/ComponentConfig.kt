package com.kirson.corporateapp.core.model

import com.bluelinelabs.conductor.Controller
import com.kirson.corporateapp.core.util.ToothpickEmptyModuleBindings
import com.kirson.corporateapp.core.util.ToothpickModuleBindings
import com.kirson.corporateapp.core.PresenterLifecycle

data class ComponentConfig(
  /**
   * A presenter class to bind into current screen module
   */
  val presenterClass: Class<out PresenterLifecycle<*, *>>,
  /**
   * A controller class to bind into current screen module
   */
  val controllerClass: Class<out Controller>,
  /**
   * A set of additional DI-bindings to associate with screen's module.
   * Will be merged into the same module in which presenter will be created.
   */
  val screenBindings: ToothpickModuleBindings = ToothpickEmptyModuleBindings,
)
