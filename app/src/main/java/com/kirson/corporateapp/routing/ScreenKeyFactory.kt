package com.kirson.corporateapp.routing

import com.kirson.corporateapp.core.model.ScreenKey
import com.kirson.corporateapp.routing.AppRoute
import com.kirson.corporateapp.ui.identification.UserIdentificationKey
import com.kirson.corporateapp.ui.main.OrdersKey
import com.kirson.corporateapp.ui.main.ProfileKey
import com.kirson.corporateapp.ui.main.ServicesKey
import com.kirson.corporateapp.ui.password.EnterPasswordKey

class ScreenKeyFactory : Function1<AppRoute, ScreenKey> {
  override fun invoke(route: AppRoute): ScreenKey {
    return when (route) {
      is AppRoute.Login.UserIdentificationKey -> UserIdentificationKey
      is AppRoute.Login.EnterPassword -> EnterPasswordKey
      is AppRoute.Services.ServicesKey -> ServicesKey
      is AppRoute.Orders.OrdersKey -> OrdersKey
      is AppRoute.Profile.ProfileKey -> ProfileKey
    }
  }
}
