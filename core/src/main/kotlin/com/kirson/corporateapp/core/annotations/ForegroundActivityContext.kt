package com.kirson.corporateapp.core.annotations

import javax.inject.Qualifier

/**
 * A qualifier to specify that injected context is a context of activity
 * which is currently in foreground
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForegroundActivityContext
