package com.example.howlonguntil.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Event(
    val eventName: String,
    val eventDate: LocalDateTime,
    @PrimaryKey(autoGenerate = true) val id: Int = 0

)
