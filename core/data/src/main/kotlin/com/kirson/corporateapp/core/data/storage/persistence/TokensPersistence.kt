package com.kirson.corporateapp.core.data.storage.persistence

import com.kirson.corporateapp.core.data.entity.AccessToken
import com.kirson.corporateapp.core.data.entity.AuthTokens
import com.kirson.corporateapp.core.data.entity.GuestToken

interface TokensPersistence {
  val guestToken: GuestToken?
  val accessToken: AccessToken?

  fun saveGuestToken(token: GuestToken)
  fun deleteGuestToken()

  fun saveAuthTokens(tokens: AuthTokens)
  fun deleteAuthTokens()
}