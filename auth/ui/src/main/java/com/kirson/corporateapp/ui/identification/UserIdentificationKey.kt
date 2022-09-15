package com.kirson.corporateapp.ui.identification

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import com.kirson.corporateapp.core.model.ComponentConfig
import com.kirson.corporateapp.core.model.ScreenKey

@Parcelize
object UserIdentificationKey : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
    presenterClass = UserIdentificationPresenter::class.java,
    controllerClass = UserIdentificationController::class.java,
  )
}
