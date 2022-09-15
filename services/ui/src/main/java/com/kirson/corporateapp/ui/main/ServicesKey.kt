package com.kirson.corporateapp.ui.main

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import com.kirson.corporateapp.core.model.ComponentConfig
import com.kirson.corporateapp.core.model.ScreenKey

@Parcelize
object ServicesKey : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
    presenterClass = ServicesPresenter::class.java,
    controllerClass = ServicesController::class.java,
  )
}
