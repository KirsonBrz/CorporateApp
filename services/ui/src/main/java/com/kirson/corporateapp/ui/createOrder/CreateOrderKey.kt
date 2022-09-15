package com.kirson.corporateapp.ui.createOrder

import com.kirson.corporateapp.core.model.ComponentConfig
import com.kirson.corporateapp.core.model.ScreenKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
object CreateOrderKey : ScreenKey() {
  // See NOTE_IGNORED_ON_PARCEL_AND_OBJECT
  @Suppress("INAPPLICABLE_IGNORED_ON_PARCEL")
  @IgnoredOnParcel
  override val componentConfig = ComponentConfig(
      presenterClass = CreateOrderPresenter::class.java,
      controllerClass = CreateOrderController::class.java,
  )
}
