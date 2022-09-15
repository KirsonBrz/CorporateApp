package com.kirson.corporateapp.ui.core.uikit.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kirson.corporateapp.core.ui.uikit.R

private val Roboto = FontFamily(
  Font(R.font.roboto_regular, FontWeight.Normal),
  Font(R.font.roboto_medium, FontWeight.Medium),
  Font(R.font.roboto_bold, FontWeight.Bold),
)

@Immutable
data class AppTypography internal constructor(
  val title : TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 34.sp,
    lineHeight = 41.sp,
  ),
  val largeTitle: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 28.sp,
    lineHeight = 34.sp,
  ),
  val subtitle: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 25.sp
  ),
  val bodyRegular: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 25.sp,
  ),
  val bodyMedium: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.SemiBold,
    fontSize = 17.sp,
    lineHeight = 22.sp,
  ),
  val body1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 17.sp,
    lineHeight = 22.sp,
  ),
  val body2: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 20.sp,
  ),
  val bodySemibold: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.SemiBold,
    fontSize = 15.sp,
    lineHeight = 20.sp,
  ),
  val caption1: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 16.sp
  ),
  val caption2:  TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
    lineHeight = 13.sp,
  ),
  val button: TextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.SemiBold,
    fontSize = 15.sp,
    lineHeight = 18.sp,
  ),
)

/**
 * Used for fallback only and shouldn't be used generally, use [AppTypography] instead.
 */
val MaterialTypography = Typography()

internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }