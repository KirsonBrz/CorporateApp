package com.kirson.corporateapp.core.domain.state

import kotlinx.coroutines.flow.Flow

interface StateStore<S : Any> {
  val state: S
  val flow: Flow<S>
  fun get(block: suspend (S) -> Unit)
  fun set(stateReducer: S.() -> S)
}
