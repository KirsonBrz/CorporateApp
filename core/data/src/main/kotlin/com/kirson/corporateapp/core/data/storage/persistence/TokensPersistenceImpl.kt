package com.kirson.corporateapp.core.data.storage.persistence

import com.kirson.corporateapp.core.util.checkBackgroundThread
import com.kirson.corporateapp.core.data.entity.AccessToken
import com.kirson.corporateapp.core.data.entity.AuthTokens
import com.kirson.corporateapp.core.data.entity.GuestToken
import com.kirson.corporateapp.core.data.storage.preferences.AppPreferences
import javax.inject.Inject

class TokensPersistenceImpl @Inject constructor(
  private val preferences: AppPreferences,
) : TokensPersistence {

  override val guestToken: GuestToken?
    get() {
      checkBackgroundThread()
      val token = preferences.getValue(PREF_KEY_GUEST_TOKEN)
      return token?.let(::GuestToken)
    }

  override val accessToken: AccessToken?
    get() {
      checkBackgroundThread()
      val accessToken = preferences.getValue(PREF_KEY_ACCESS_TOKEN)
      return accessToken?.let(::AccessToken)
    }

  override fun saveGuestToken(token: GuestToken) {
    checkBackgroundThread()
    preferences.saveValue(PREF_KEY_GUEST_TOKEN, token.value)
  }

  override fun deleteGuestToken() {
    checkBackgroundThread()
    preferences.removeValue(PREF_KEY_GUEST_TOKEN)
  }

  override fun saveAuthTokens(tokens: AuthTokens) {
    checkBackgroundThread()
    preferences.saveValue(PREF_KEY_ACCESS_TOKEN, tokens.accessToken.value)
    preferences.saveValue(PREF_KEY_REFRESH_TOKEN, tokens.refreshToken.value)
  }

  override fun deleteAuthTokens() {
    checkBackgroundThread()
    preferences.removeValue(PREF_KEY_ACCESS_TOKEN)
    preferences.removeValue(PREF_KEY_REFRESH_TOKEN)
  }
}

private const val PREF_KEY_GUEST_TOKEN = "guest_token_key"
private const val PREF_KEY_ACCESS_TOKEN = "access_token_key"
private const val PREF_KEY_REFRESH_TOKEN = "refresh_token_key"
