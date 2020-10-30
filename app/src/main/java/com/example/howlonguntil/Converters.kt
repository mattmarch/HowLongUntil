package com.example.howlonguntil

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @TypeConverter
    fun fromLocalDate(value: LocalDateTime): String {
        return value.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDateTime {
        return LocalDateTime.parse(value, formatter)
    }
}