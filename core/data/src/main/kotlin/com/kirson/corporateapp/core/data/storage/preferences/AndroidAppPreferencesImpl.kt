package com.kirson.corporateapp.core.data.storage.preferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.kirson.corporateapp.core.annotations.ApplicationContext
import javax.inject.Inject

/**
 * An implementation of [AppPreferences] which reads/writes from/to [SharedPreferences]
 * @property settingsName a name of shared preferences file to use.
 * Default preferences will be used if nothing is passed
 */
internal class AndroidAppPreferencesImpl : AppPreferences {

  private val context: Context
  private val settingsName: String?

  @Inject
  constructor(@ApplicationContext context: Context) : this(context, settingsName = null)

  constructor(context: Context, settingsName: String? = null) {
    this.context = context
    this.settingsName = settingsName
  }

  override fun getValue(key: String): String? {
    return getPrefs().getString(key, null)
  }

  override fun containsKey(key: String): Boolean {
    return getPrefs().contains(key)
  }

  @SuppressLint("ApplySharedPref")
  override fun saveValue(key: String, value: String, forceFlush: Boolean): String {
    getPrefs().edit()
      .putString(key, value)
      .apply { if (forceFlush) commit() else apply() }
    return value
  }

  @SuppressLint("ApplySharedPref")
  override fun removeValue(key: String, forceFlush: Boolean): Boolean {
    val prefs = getPrefs()
    return if (prefs.contains(key)) {
      prefs.edit()
        .remove(key)
        .apply { if (forceFlush) commit() else apply() }
      true
    } else {
      false
    }
  }

  private fun getPrefs(): SharedPreferences {
    return if (settingsName != null)
      context.getSharedPreferences(settingsName, Context.MODE_PRIVATE)
    else
      PreferenceManager.getDefaultSharedPreferences(context)
  }
}
