package com.kirson.corporateapp.core.data.di

import com.kirson.corporateapp.core.util.ToothpickModuleBindings
import com.kirson.corporateapp.core.data.network.di.NetworkBindings
import com.kirson.corporateapp.core.data.storage.di.StorageBindings
import toothpick.config.Module

object DataBindings : ToothpickModuleBindings {
  override fun bindInto(module: Module) {
    NetworkBindings.bindInto(module)
    StorageBindings.bindInto(module)
  }
}