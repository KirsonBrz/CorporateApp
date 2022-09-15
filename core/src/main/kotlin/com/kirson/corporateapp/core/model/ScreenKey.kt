package com.kirson.corporateapp.core.model

import android.content.Context
import android.os.Parcelable
import com.bluelinelabs.conductor.Controller
import com.kirson.corporateapp.core.annotations.ForegroundActivityContext
import com.kirson.corporateapp.core.di.foregroundScopeName

/**
 * A routing key representing the screen.
 * Create a Parcelable data class which inherits this class, add all required
 * screen arguments into that Parcelable and then call [createController] -
 * they will be passed into the [Controller] to use them.
 *
 * [com.kirson.corporateapp.ui.mvi.core.BaseController] implementations can access their keys via
 * [com.kirson.corporateapp.ui.mvi.core.BaseController.key] function
 *
 * Example implementation:
 *
 * ```
 * @Parcelize
 * data class OrderListKey(
 *   val listId: String
 * ) : ScreenKey() {
 *   override fun createController(): Controller {
 *     return OrderListController()
 *   }
 * }
 *
 * // ...to create Controller use ScreenKey.newController()
 *
 * val key = OrderListKey(listId = "some_id")
 * val controller = key.newController()
 *
 * // ...inside your Controller subclass use BaseMviController.key() to retrieve key values
 *
 * class OrderListController {
 *   override fun initializeView(...) {
 *     val key = key<OrderListKey>()
 *     Timber.d("list id is: ${key.listId}")
 *   }
 * }
 * ```
 */
abstract class ScreenKey : Parcelable {
  protected abstract val componentConfig: ComponentConfig

  // See NOTE_UNIQUE_DEFAULT_SCOPE_NAME
  private fun createScreenScopeName() = this.javaClass.simpleName + System.currentTimeMillis().toString()

  /**
   * Creates a new [Controller] by passing `this` Parcelable to it as an arguments to use.
   *
   * By default base controllers will open a new Toothpick scope on top of a [foregroundScopeName]
   * to inject their dependencies into that scope.
   *
   * Use [parentScopeName] to pass another scope to use as a parent scope
   */
  fun createController(@ForegroundActivityContext context: Context, parentScopeName: Any? = null): Controller {
    if (parentScopeName != null) {
      require(parentScopeName is String) { "scope names are required to be strings, got: ${parentScopeName.javaClass}" }
    }
    return componentConfig.controllerClass.newInstance().apply {
      val resolvedParentScopeName = (parentScopeName as String?) ?: foregroundScopeName(context)
      val screenScopeName = createScreenScopeName()
      args.putParcelable(SCREEN_KEY_ARG_NAME, this@ScreenKey)
      args.putString(SCREEN_SCOPE_ARG_NAME, screenScopeName)

      addLifecycleListener(ScreenLifecycle(componentConfig, resolvedParentScopeName, screenScopeName))
    }
  }
}

internal const val SCREEN_KEY_ARG_NAME = "key"
internal const val SCREEN_SCOPE_ARG_NAME = "screen_scope"

// NOTE_IGNORED_ON_PARCEL_AND_OBJECT
// Если добавить override val в object, который менеджится через @Parcelize,
// то это property начнёт подсвечиваться warning-ом, что надо поставить @IgnoreOnParcel.
// Но если его поставить, то IDE начинает ругаться, что для object-ов это не имеет смысла.
// Есть баг на эту тему: https://issuetracker.google.com/issues/177856518
// Я решил оставить @IgnoreOnParcel над этим свойством на случай, если object однажды превратиться
// в class. Тогда можно будет убрать @Suppress и всё.
//
// Если баг починят, то можно убрать @Suppress для этих случаев отовсюду сразу

// NOTE_UNIQUE_DEFAULT_SCOPE_NAME
// Adding a unique number/id to a default scope name helps to cope with situations when controller is used
// as a nested child controller (in pager) and parent controller has view recreated: in this case
// parent controller can first destroy old instances in a pager and create new instances.
// In such cases it can happen like this:
//  - newChild.onContextAvailable() (opens scope)
//  - oldChild.onDestroy() (closes scope)
//  - newChild.createPresenter()
// if scope had the same name in both child, createPresenter would crash, because scope is closed
