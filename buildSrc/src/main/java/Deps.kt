@Suppress("MemberVisibilityCanBePrivate", "unused")
object Deps {
  const val kotlinVersion = "1.5.31"

  const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
  const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
  const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
  const val kotlinCompiler = "org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion"

  private const val coroutinesVersion = "1.5.1"
  const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
  const val kotlinCoroutinesRx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$coroutinesVersion"
  const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

  const val composeVersion = "1.0.4"
  const val composeUi = "androidx.compose.ui:ui:$composeVersion"
  const val composeMaterial = "androidx.compose.material:material:$composeVersion"
  const val composeMaterialIcons = "androidx.compose.material:material-icons-core:$composeVersion"
  const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
  const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"

  private const val accompanistVersion = "0.18.0"
  const val accompanistInsets = "com.google.accompanist:accompanist-insets:$accompanistVersion"
  const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
  const val accompanistPager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
  const val accompanistPagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
  const val accompanistSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"
  const val accompanistFlowLayout = "com.google.accompanist:accompanist-flowlayout:$accompanistVersion"

  private const val agpVersion = "7.0.3"
  const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"

  const val timber = "com.jakewharton.timber:timber:5.0.1"

  val coreLibs = arrayOf(
    kotlinStdLib,
    kotlinReflect,
    kotlinCoroutinesCore,
    kotlinCoroutinesAndroid,
    kotlinCoroutinesRx2,
    "com.michael-bull.kotlin-result:kotlin-result:1.1.9",
    "com.jakewharton.rxrelay2:rxrelay:2.1.1",
  )

  private const val supportLibVersion = "1.1.0"
  const val supportAnnotations = "androidx.annotation:annotation:1.1.0"
  val supportCoreLibs = arrayOf(
    supportAnnotations,
    "androidx.core:core:1.3.2",
    "androidx.core:core-ktx:1.3.2"
  )
  const val supportActivity = "androidx.activity:activity-ktx:1.2.0-rc01"
  val supportUiLibs = arrayOf(
    "androidx.appcompat:appcompat:1.3.0-beta01",
    supportActivity,
    "com.google.android.material:material:1.3.0-alpha04"
  )
  const val supportPreferences = "androidx.preference:preference-ktx:1.1.1"

  const val conductor = "com.bluelinelabs:conductor:3.0.0"

  private const val okHttpVersion = "4.9.0"
  private const val okioVersion = "2.9.0"
  private const val moshiVersion = "1.12.0"

  const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
  const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
  const val okio = "com.squareup.okio:okio:$okioVersion"
  const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
  const val moshiProcessor = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

  private const val retrofitVersion = "2.9.0"
  val retrofitLibs = arrayOf(
    "com.squareup.retrofit2:retrofit:$retrofitVersion",
    "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
  )

  private const val toothpickVersion = "2.1.0"
  const val javaxInject = "javax.inject:javax.inject:1"
  const val toothpickRuntime = "com.github.stephanenicolas.toothpick:toothpick-runtime:$toothpickVersion"
  const val toothpickProcessor = "com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion"

  private const val coilVersion = "1.3.2"
  const val coil = "io.coil-kt:coil-base:$coilVersion"
  const val coilCompose = "io.coil-kt:coil-compose:$coilVersion"

  const val unicornCoroutines = "ru.dimsuz:unicorn-coroutines:0.9.1"

  const val sqlDelightPlugin = "com.squareup.sqldelight:gradle-plugin:1.5.2"
  const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:1.5.2"
  const val sqlDelightRxCoroutines = "com.squareup.sqldelight:coroutines-extensions:1.5.2"

  const val firebaseCore = "com.google.firebase:firebase-core:18.0.2"
  const val firebaseMessaging = "com.google.firebase:firebase-messaging:21.0.1"
}
