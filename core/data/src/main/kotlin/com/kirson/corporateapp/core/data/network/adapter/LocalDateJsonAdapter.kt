package com.kirson.corporateapp.core.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateJsonAdapter(private val formatter: DateTimeFormatter) {
  @FromJson
  fun fromJson(dateJson: String): LocalDate {
    return LocalDate.parse(dateJson, formatter)
  }

  @ToJson
  fun toJson(dateTime: LocalDate): String {
    return dateTime.format(formatter)
  }
}
