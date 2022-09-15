package com.kirson.corporateapp.core.util

import android.app.Activity
import android.content.Context
import com.bluelinelabs.conductor.Controller
import com.kirson.corporateapp.core.di.APP_SCOPE_NAME
import com.kirson.corporateapp.core.di.appScope
import com.kirson.corporateapp.core.di.foregroundScopeName
import toothpick.Scope
import toothpick.Toothpick

/**
 * A scope which is open only when app is in "foreground":
 * when app activity is on the screen and/or is available.
 * This scope has [appScope] as a parent scope and
 * contains additional dependencies, such as router
 *
 * Use [foregroundScopeName] to reference it by name if [Activity.foregroundScope] is not available, otherwise you can
 * call [Scope.getName] on this scope
 *
 * **IMPORTANT** must be called after MainActivity is created, otherwise will not contain necessary dependencies
 */
inline val Activity.foregroundScope: Scope get() = Toothpick.openScopes(APP_SCOPE_NAME, foregroundScopeName(this))

/**
 * A scope which is open during whole application lifetime.
 * Contains globally important dependencies
 *
 * Use [APP_SCOPE_NAME] to reference it by name if [Context.appScope] is not available, otherwise you can call
 * [Scope.getName] on this scope
 */
inline val Controller.appScope: Scope get() = requireActivity.appScope

/**
 * A scope which is open only when app is in "foreground":
 * when app activity is on the screen and/or is available.
 * This scope has [appScope] as a parent scope and
 * contains additional dependencies, such as router
 *
 * Use [foregroundScopeName] to reference it by name if [Activity.foregroundScope] is not available, otherwise you can
 * call [Scope.getName] on this scope
 *
 * **IMPORTANT** must be called after MainActivity is created, otherwise will not contain necessary dependencies
 */
inline val Controller.foregroundScope: Scope get() = requireActivity.foregroundScope
