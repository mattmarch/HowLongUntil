package com.example.howlonguntil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.howlonguntil.entities.Event
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var eventViewModel: EventsViewModel

    private val newEventActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = EventListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        eventViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(EventsViewModel::class.java)

        eventViewModel.events.observe(this, Observer { events ->
            events?.let { adapter.setEvents(it) }
        })
    }

    fun onAddEventClick(view: View) {
        val intent = Intent(this@MainActivity, NewEventActivity::class.java)
        startActivityForResult(intent, newEventActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != newEventActivityRequestCode) {
            return
        }
        if (resultCode == Activity.RESULT_OK) {
            eventViewModel.insert(Event("Test new event", LocalDateTime.now()))
        } else {
            Toast.makeText(
                applicationContext,
                getString(R.string.event_not_saved_toast),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}