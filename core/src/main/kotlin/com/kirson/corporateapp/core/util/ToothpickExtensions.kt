package com.kirson.corporateapp.core.util

import toothpick.Scope

inline fun <reified T> Scope.instance(): T = this.getInstance(T::class.java)
