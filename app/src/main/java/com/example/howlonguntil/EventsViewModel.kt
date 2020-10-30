package com.example.howlonguntil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.howlonguntil.entities.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: EventRepository

    val events: LiveData<List<Event>>

    init {
        val eventDao = HowLongUntilDatabase.getDatabase(application, viewModelScope).eventDao()
        repository = EventRepository(eventDao)
        events = repository.allEvents
    }

    fun insert(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }

    fun delete(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(event)
    }

}