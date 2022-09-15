package com.kirson.corporateapp.core.annotations

import javax.inject.Qualifier

/**
 * A qualifier to specify that injected context is application context
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext
