package com.kirson.corporateapp.core

import androidx.compose.runtime.Stable
import com.jakewharton.rxrelay2.Relay
import io.reactivex.functions.Consumer
import java.util.UUID

data class UiIntent internal constructor(
  val id: Long,
  val factoryId: String,
  val name: String,
  val payload: Any
)

interface UiIntentFactory {
  val name: String
  fun isOwnerOf(intent: UiIntent): Boolean

  override fun equals(other: Any?): Boolean
  override fun hashCode(): Int
}

@Stable
class UiIntentFactory0 internal constructor(
  override val name: String,
  sendRelay: Relay<UiIntent>,
) : Function0<Unit>, Consumer<Unit>, UiIntentFactory {
  private val wrapped = UiIntentFactory1<Unit>(name, sendRelay)

  override fun invoke() {
    wrapped.invoke(Unit)
  }

  override fun accept(v: Unit) {
    wrapped.invoke(Unit)
  }

  override fun isOwnerOf(intent: UiIntent): Boolean {
    return wrapped.isOwnerOf(intent)
  }

  override fun equals(other: Any?): Boolean {
    return when {
      this === other -> true
      other is UiIntentFactory0 -> this.wrapped == other.wrapped
      else -> this.wrapped == other
    }
  }

  override fun hashCode(): Int {
    return wrapped.hashCode()
  }

  fun addObserver(action: () -> Unit): UiIntentFactory0 {
    wrapped.addObserver { action.invoke() }
    return this
  }

  fun removeObserver(action: () -> Unit): UiIntentFactory0 {
    wrapped.removeObserver { action.invoke() }
    return this
  }
}

@Stable
class UiIntentFactory1<T : Any> internal constructor(
  override val name: String,
  private val sendRelay: Relay<UiIntent>
) : Function1<T, Unit>, Consumer<T>, UiIntentFactory {
  private val factoryId = UUID.randomUUID().toString()
  private var nextId = 0L
  private var observers: List<(T) -> Unit> = emptyList()

  override fun isOwnerOf(intent: UiIntent) = intent.factoryId == factoryId

  override fun invoke(payload: T) {
    sendRelay.accept(UiIntent(nextId++, factoryId, name, payload))
    observers.forEach { it.invoke(payload) }
  }

  override fun accept(v: T) {
    invoke(v)
  }

  fun addObserver(action: (T) -> Unit): UiIntentFactory1<T> {
    observers = observers.plus(action)
    return this
  }

  fun removeObserver(action: (T) -> Unit): UiIntentFactory1<T> {
    observers = observers.minus(action)
    return this
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as UiIntentFactory1<*>
    if (factoryId != other.factoryId) return false
    return true
  }

  override fun hashCode(): Int {
    return factoryId.hashCode()
  }
}
