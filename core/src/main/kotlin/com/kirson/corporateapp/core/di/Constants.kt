package com.kirson.corporateapp.core.di

import android.content.Context
import com.kirson.corporateapp.core.annotations.ForegroundActivityContext
import toothpick.Scope
import toothpick.Toothpick

/**
 * A scope which is open during whole application lifetime.
 * Contains globally important dependencies
 */
const val APP_SCOPE_NAME = "APP_SCOPE"

/**
 * A scope which is open during whole application lifetime.
 * Contains globally important dependencies.
 *
 * Use [APP_SCOPE_NAME] to reference it by name if [Context.appScope] is not available, otherwise you can call
 * [Scope.getName] on this scope
 */
inline val Context.appScope: Scope get() = Toothpick.openScopes(APP_SCOPE_NAME)

/**
 * A scope which is open only when app is in "foreground":
 * when app activity is on the screen and/or is available.
 * This scope has a parent scope and
 * contains additional dependencies, such as router
 */
fun foregroundScopeName(@ForegroundActivityContext context: Context): String {
  // Uses hashCode to make scope conflicts not possible on activity restarts.
  // Otherwise if scope name would be constant the following can happen:
  //
  // MainActivity.onCreate() // activity instance #1, opens foregroundScope with name 'FAS_NAME1'
  // ProcessPhoenix.triggerRebirth()
  // MainActivity.onCreate() // activity instance #2, opens foregroundScope with name 'FAS_NAME1'
  // MainActivity.onDestroy() // activity instance #1, CLOSES foregroundScope with name 'FAS_NAME1'
  //
  // All this happens in the same process.
  // Toothpick has only one root scope, so FAS_NAME1 will be shared between activity instances.
  return "FOREGROUND_APP_SCOPE_${Integer.toHexString(context.hashCode())}"
}
