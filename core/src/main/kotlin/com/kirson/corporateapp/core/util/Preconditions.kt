package com.kirson.corporateapp.core.util

import android.os.Looper

fun checkMainThread() {
  check(Looper.getMainLooper() == Looper.myLooper()) {
    "Expected to be called on main thread but was ${Thread.currentThread().name}"
  }
}

fun checkBackgroundThread() {
  check(Looper.getMainLooper() != Looper.myLooper()) {
    "Expected to be called on background thread but was ${Thread.currentThread().name}"
  }
}
