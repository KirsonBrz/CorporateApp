package com.kirson.corporateapp.core.data.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeJsonAdapter(private val formatter: DateTimeFormatter) {
  @FromJson
  fun fromJson(dateJson: String): LocalTime {
    return LocalTime.parse(dateJson, formatter)
  }

  @ToJson
  fun toJson(dateTime: LocalTime): String {
    return dateTime.format(formatter)
  }
}
