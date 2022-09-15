package com.kirson.corporateapp.core.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeJsonAdapter(private val formatter: DateTimeFormatter) {
  @FromJson
  fun fromJson(dateJson: String): LocalDateTime {
    return LocalDateTime.parse(dateJson, formatter)
  }

  @ToJson
  fun toJson(dateTime: LocalDateTime): String {
    return dateTime.format(formatter)
  }
}
