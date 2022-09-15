package com.kirson.corporateapp.ui.main

import com.kirson.corporateapp.core.model.ComponentConfig
import com.kirson.corporateapp.core.model.ScreenKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
object OrdersKey : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
    presenterClass = OrdersPresenter::class.java,
    controllerClass = OrdersController::class.java,
  )
}
