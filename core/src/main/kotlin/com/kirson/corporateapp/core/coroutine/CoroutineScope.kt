package com.kirson.corporateapp.core.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

// Copy of MainScope() which uses "immediate" dispatcher
@Suppress("FunctionName")
internal fun MainScopeImmediate(): CoroutineScope = ContextScope(SupervisorJob() + Dispatchers.Main.immediate)

// copied from kotlinx.coroutines.CoroutineScope
internal class ContextScope(context: CoroutineContext) : CoroutineScope {
  override val coroutineContext: CoroutineContext = context
  // CoroutineScope is used intentionally for user-friendly representation
  override fun toString(): String = "CoroutineScope(coroutineContext=$coroutineContext)"
}
