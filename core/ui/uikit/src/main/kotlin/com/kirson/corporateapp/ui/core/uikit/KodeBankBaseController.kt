package com.kirson.corporateapp.ui.core.uikit

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets
import com.kirson.corporateapp.core.BaseComposeController
import com.kirson.corporateapp.core.BaseViewIntents
import com.kirson.corporateapp.core.util.foregroundScope
import com.kirson.corporateapp.core.util.instance
import com.kirson.corporateapp.ui.core.uikit.theme.AppTheme

abstract class KodeBankBaseController<VS : Any, VI : BaseViewIntents> : BaseComposeController<VS, VI>() {
  private lateinit var imageLoader: ImageLoader

  override fun onContextAvailable(context: Context) {
    super.onContextAvailable(context)
    imageLoader = foregroundScope.instance()
  }

  @Composable
  final override fun Content(state: VS) {
    AppTheme {
      ProvideWindowInsets(consumeWindowInsets = false) {
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
          ScreenContent(state)
        }
      }
    }
  }

  @Composable
  abstract fun ScreenContent(state: VS)
}