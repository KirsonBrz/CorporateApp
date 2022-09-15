package com.kirson.corporateapp.ui.core.uikit.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
  backgroundPrimary: Color,
  backgroundSecondary: Color,
  contendPrimary: Color,
  contendSecondary: Color,
  contendTertiary: Color,
  contendAccentPrimary: Color,
  contendAccentSecondary: Color,
  contendAccentTertiary: Color,
  textPrimary: Color,
  textSecondary: Color,
  textTertiary: Color,
  indicatorContendError: Color,
  indicatorContendDone: Color,
  indicatorContendSuccess: Color,
  primaryButton: Color,
  bottomMenuBackground: Color,
  scrimer: Color,
  calendarPeriod: Color,
  buttonSecondary: Color,
  textButton: Color,
  black: Color,
  isDark: Boolean,
) {
  var backgroundPrimary by mutableStateOf(backgroundPrimary)
    private set
  var backgroundSecondary by mutableStateOf(backgroundSecondary)
    private set
  var contendPrimary by mutableStateOf(contendPrimary)
    private set
  var contendSecondary by mutableStateOf(contendSecondary)
    private set
  var contendTertiary by mutableStateOf(contendTertiary)
    private set
  var contendAccentPrimary by mutableStateOf(contendAccentPrimary)
    private set
  var contendAccentSecondary by mutableStateOf(contendAccentSecondary)
    private set
  var contendAccentTertiary by mutableStateOf(contendAccentTertiary)
    private set
  var textPrimary by mutableStateOf(textPrimary)
    private set
  var textSecondary by mutableStateOf(textSecondary)
    private set
  var textTertiary by mutableStateOf(textTertiary)
    private set
  var indicatorContendError by mutableStateOf(indicatorContendError)
    private set
  var indicatorContendDone by mutableStateOf(indicatorContendDone)
    private set
  var indicatorContendSuccess by mutableStateOf(indicatorContendSuccess)
    private set
  var primaryButton by mutableStateOf(primaryButton)
    private set
  var bottomMenuBackground by mutableStateOf(bottomMenuBackground)
    private set
  var scrimer by mutableStateOf(scrimer)
    private set
  var calendarPeriod by mutableStateOf(calendarPeriod)
    private set
  var buttonSecondary by mutableStateOf(buttonSecondary)
    private set
  var textButton by mutableStateOf(textButton)
    private set
  var black by mutableStateOf(black)
    private set
  var isDark by mutableStateOf(isDark)
    private set

  fun update(other: AppColors) {
    backgroundPrimary = other.backgroundPrimary
    backgroundSecondary = other.backgroundSecondary
    contendPrimary = other.contendPrimary
    contendSecondary = other.contendSecondary
    contendTertiary = other.contendTertiary
    contendAccentPrimary = other.contendAccentPrimary
    contendAccentSecondary = other.contendAccentSecondary
    contendAccentTertiary = other.contendAccentTertiary
    textPrimary = other.textPrimary
    textSecondary = other.textSecondary
    textTertiary = other.textTertiary
    indicatorContendError = other.indicatorContendError
    indicatorContendDone = other.indicatorContendDone
    indicatorContendSuccess = other.indicatorContendSuccess
    primaryButton = other.primaryButton
    bottomMenuBackground = other.bottomMenuBackground
    scrimer = other.scrimer
    calendarPeriod = other.calendarPeriod
    buttonSecondary = other.buttonSecondary
    textButton = other.textButton
    black = other.black
    isDark = other.isDark
  }
}
