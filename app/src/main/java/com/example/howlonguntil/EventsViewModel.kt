package com.example.howlonguntil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.howlonguntil.entities.Event
import java.time.LocalDate

class EventsViewModel(application: Application): AndroidViewModel(application) {

    val events: List<Event> = listOf(
        Event("🎄 Christmas", LocalDate.of(2020, 12, 25)),
        Event("🎃 Halloween", LocalDate.of(2020, 10, 31))
    )

}