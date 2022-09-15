package com.kirson.corporateapp

import android.content.Context
import coil.ImageLoader
import okhttp3.OkHttpClient
import com.kirson.corporateapp.core.annotations.ApplicationContext
import com.kirson.corporateapp.core.data.di.DataBindings
import toothpick.config.Module
import javax.inject.Inject
import javax.inject.Provider

internal class KodeApplicationModule(application: CorporateApplication) : Module() {
  init {
    bind(Context::class.java).withName(ApplicationContext::class.java).toInstance(application)
    bind(CorporateApplication::class.java).toInstance(application)
    bind(ImageLoader::class.java).toProvider(ImageLoaderProvider::class.java).providesSingletonInScope()

    DataBindings.bindInto(this)
  }
}

private class ImageLoaderProvider @Inject constructor(
  @ApplicationContext private val context: Context,
  private val okHttpClient: OkHttpClient,
) : Provider<ImageLoader> {
  override fun get(): ImageLoader {
    return ImageLoader.Builder(context)
      .okHttpClient(okHttpClient)
      .build()
  }
}
