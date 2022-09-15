package com.kirson.corporateapp.core.data.storage.di

import com.squareup.sqldelight.db.SqlDriver
import com.kirson.corporateapp.core.util.ToothpickModuleBindings
import com.kirson.corporateapp.core.data.storage.InMemoryDatabaseDriverProvider
import com.kirson.corporateapp.core.data.storage.preferences.di.AppPreferencesBindings
import toothpick.config.Module

internal object StorageBindings : ToothpickModuleBindings {
  override fun bindInto(module: Module) {
    module.bind(SqlDriver::class.java)
      .toProvider(InMemoryDatabaseDriverProvider::class.java)
      .providesSingletonInScope()

    AppPreferencesBindings.bindInto(module)
  }
}