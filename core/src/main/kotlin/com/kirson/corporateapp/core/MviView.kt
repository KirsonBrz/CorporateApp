
package com.kirson.corporateapp.core

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

interface MviView<in VS : Any, out VI : BaseViewIntents> {
  fun render(viewState: VS)
  val intents: VI
}

open class BaseViewIntents {
  private val intentRelay = PublishRelay.create<UiIntent>()

  internal val stream: Observable<UiIntent> = intentRelay

  fun intent(name: String = "name-${System.nanoTime()}"): UiIntentFactory0 {
    return UiIntentFactory0(name, intentRelay)
  }

  fun <T : Any> intent(name: String = "name-${System.nanoTime()}"): UiIntentFactory1<T> {
    return UiIntentFactory1(name, intentRelay)
  }
}
