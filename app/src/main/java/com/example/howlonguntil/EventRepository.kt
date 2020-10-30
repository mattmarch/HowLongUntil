package com.example.howlonguntil

import androidx.lifecycle.LiveData
import com.example.howlonguntil.entities.Event

class EventRepository(private val eventDao: EventDao) {

    val allEvents: LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

}