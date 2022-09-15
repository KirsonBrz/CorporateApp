package com.kirson.corporateapp

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import com.kirson.corporateapp.core.annotations.ForegroundActivityContext
import com.kirson.corporateapp.core.annotations.RouterScreenFactory
import com.kirson.corporateapp.core.routing.ConductorAppRouter
import com.kirson.corporateapp.core.routing.Router
import com.kirson.corporateapp.routing.ScreenKeyFactory
import toothpick.config.Module

internal class MainActivityModule(activity: Activity, coroutineScope: CoroutineScope) : Module() {
  init {
    bind(Context::class.java).withName(ForegroundActivityContext::class.java).toInstance(activity)
    bind(Router::class.java).to(ConductorAppRouter::class.java).singletonInScope()
    bind(Function1::class.java).withName(RouterScreenFactory::class.java).toInstance(ScreenKeyFactory())
    bind(CoroutineScope::class.java).toInstance(coroutineScope)
  }
}
