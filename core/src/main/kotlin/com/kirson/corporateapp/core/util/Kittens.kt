package com.kirson.corporateapp.core.util

import java.util.UUID
import kotlin.random.Random

fun randomImageUrl(cacheKey: Any? = null): String {
  val size = Random.Default.nextInt(from = 400, until = 1000)
  return buildString {
    append("https://picsum.photos/")
    append(size)
    append('/')
    append(size)
    if (cacheKey != null) {
      append("?__key=")
      append(cacheKey)
    }
  }
}

fun randomUuid() = UUID.randomUUID().toString()
