package com.kirson.corporateapp.core.data.storage.preferences

interface AppPreferences {
  fun saveValue(key: String, value: String, forceFlush: Boolean = false): String
  fun getValue(key: String): String?
  fun containsKey(key: String): Boolean
  fun removeValue(key: String, forceFlush: Boolean = false): Boolean
}
