package com.kirson.corporateapp

import android.app.Application
import com.kirson.corporateapp.core.di.APP_SCOPE_NAME
import timber.log.Timber
import toothpick.Toothpick

class CorporateApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    configureAppScope()
    configureLogging()
  }

  private fun configureAppScope() {
    val appScope = Toothpick.openScope(APP_SCOPE_NAME)
    appScope.installModules(
      KodeApplicationModule(this),
    )
  }

  private fun configureLogging() {
    if (!BuildConfig.RELEASE) Timber.plant(Timber.DebugTree())
  }
}
