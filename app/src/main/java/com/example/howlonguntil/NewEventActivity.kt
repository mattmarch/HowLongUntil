package com.example.howlonguntil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
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

    fun onSubmit(view: View) {
        val titleInput = findViewById<TextView>(R.id.newEventTitle)
        val datePicker = findViewById<DatePicker>(R.id.newEventDate)
        val timePicker = findViewById<TimePicker>(R.id.newEventTime)

        if (titleInput.text.isBlank()) {
            Toast.makeText(
                applicationContext,
                "Title must not be empty",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_TITLE, titleInput.text.toString())
        val date = LocalDateTime.of(datePicker.year, datePicker.month, datePicker.dayOfMonth, timePicker.hour, timePicker.minute)
        intent.putExtra(EXTRA_DATETIME, date.toEpochSecond(ZoneOffset.UTC))

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_TITLE = "com.example.howlonguntil.TITLE"
        const val EXTRA_DATETIME = "com.example.howlonguntil.DATETIME"
    }

}