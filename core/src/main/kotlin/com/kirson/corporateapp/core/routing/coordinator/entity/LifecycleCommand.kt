package com.kirson.corporateapp.core.routing.coordinator.entity

data class LifecycleCommand<R>(
  val start: () -> Unit,
  val finish: (result: R) -> Unit,
)
