package com.kirson.corporateapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import com.bluelinelabs.conductor.Controller
import com.kirson.corporateapp.core.model.SCREEN_SCOPE_ARG_NAME
import toothpick.Scope
import toothpick.Toothpick

abstract class BaseComposeController<VS : Any, VI : BaseViewIntents> : Controller, MviView<VS, VI> {

  /**
   * A base interface for configuring BaseMviController.
   * Abstract subclasses may wish to extend this config interface by adding some fields to it
   */
  interface Config<VI : BaseViewIntents> {
    val intentsConstructor: () -> VI
  }

  /**
   * Contains a cached copy of configuration created by [createConfig] during controller
   * construction
   */
  protected val config: Config<VI> by lazy(LazyThreadSafetyMode.NONE) { createConfig() }

  private var rootViewInternal: ComposeView? = null
  protected val rootView: ComposeView
    get() = rootViewInternal ?: error("attempt to get rootView before onCreateView or after onDestroyView")
  protected val rootViewSafe get() = rootViewInternal

  protected abstract fun createConfig(): Config<VI>

  @Stable
  override val intents = config.intentsConstructor()

  protected val screenScope: Scope @Stable get() {
    val name = args.getString(SCREEN_SCOPE_ARG_NAME)
      ?: error("internal error, no screen scope for ${this.javaClass.simpleName}")
    if (!Toothpick.isScopeOpen(name)) {
      error("attempt to access scope of the destroyed screen ${this.javaClass.simpleName}")
    }
    return Toothpick.openScope(name)
  }

  constructor()
  constructor(args: Bundle) : super(args)

  private var viewState by mutableStateOf<VS?>(value = null)

  final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
    rootViewInternal = ComposeView(container.context).apply {
      setContent {
        viewState?.also {
          Content(state = it)
        }
      }
    }
    return rootViewInternal!!
  }

  final override fun onDestroyView(view: View) {
    super.onDestroyView(view)
    destroyView(view)
    rootViewInternal = null
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  open fun destroyView(view: View) {}

  final override fun render(viewState: VS) {
    this.viewState = viewState
  }

  @Composable
  abstract fun Content(state: VS)
}
