package com.kirson.corporateapp.routing

import com.kirson.corporateapp.core.routing.Route

/**
 * Contains all application routes
 */
sealed class AppRoute(override val path: String) : Route {
  sealed class Login(path: String) : AppRoute(path) {
    object UserIdentificationKey : Login("/login/user_identification")
    object EnterPassword : Login("/login/password")
  }
  sealed class Services(path: String) : AppRoute(path) {
    object ServicesKey : Services("/services/main")
  }
  sealed class Orders(path: String) : AppRoute(path) {
    object OrdersKey : Orders("/orders/main")
  }
  sealed class Profile(path: String) : AppRoute(path) {
    object ProfileKey : Profile("/profile/main")
  }
}
