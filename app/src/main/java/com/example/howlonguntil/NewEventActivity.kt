package com.example.howlonguntil

import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.ZoneOffset

class NewEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)
    }

    override fun onStart() {
        super.onStart()

        val datePicker = findViewById<DatePicker>(R.id.newEventDate)
        val timePicker = findViewById<TimePicker>(R.id.newEventTime)

        datePicker.minDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000
        timePicker.hour = 0
        timePicker.minute = 0
    }

}