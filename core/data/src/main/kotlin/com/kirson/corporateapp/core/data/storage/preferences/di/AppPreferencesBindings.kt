package com.kirson.corporateapp.core.data.storage.preferences.di

import com.kirson.corporateapp.core.util.ToothpickModuleBindings
import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistence
import com.kirson.corporateapp.core.data.storage.persistence.TokensPersistenceImpl
import com.kirson.corporateapp.core.data.storage.preferences.AndroidAppPreferencesImpl
import com.kirson.corporateapp.core.data.storage.preferences.AppPreferences
import toothpick.config.Module

object AppPreferencesBindings : ToothpickModuleBindings {
  override fun bindInto(module: Module) {
    with(module) {
      bind(AppPreferences::class.java)
        .to(AndroidAppPreferencesImpl::class.java)
        .singletonInScope()

      bind(TokensPersistence::class.java)
        .to(TokensPersistenceImpl::class.java)
        .singletonInScope()
    }
  }
}
