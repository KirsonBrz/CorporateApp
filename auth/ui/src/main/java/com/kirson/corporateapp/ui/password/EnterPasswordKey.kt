package com.kirson.corporateapp.ui.password

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import com.kirson.corporateapp.core.model.ComponentConfig
import com.kirson.corporateapp.core.model.ScreenKey

@Parcelize
object EnterPasswordKey : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
    presenterClass = EnterPasswordPresenter::class.java,
    controllerClass = EnterPasswordController::class.java,
  )
}
