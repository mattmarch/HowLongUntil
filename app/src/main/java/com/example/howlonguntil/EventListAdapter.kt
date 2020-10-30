package com.example.howlonguntil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.howlonguntil.entities.Event
import java.time.Duration
import java.time.LocalDateTime

class EventListAdapter(context: Context, val deleteEvent: (event: Event) -> Unit) :
    RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventItemTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        val eventItemTimeUntil = itemView.findViewById<TextView>(R.id.timeUntil)
        val eventItemDeleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val eventView = inflater.inflate(R.layout.event_item, parent, false)
        return EventViewHolder(eventView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        holder.eventItemTitle.text = current.eventName
        holder.eventItemTimeUntil.text = datesToTimeUntilString(current.eventDate, LocalDateTime.now())
        holder.eventItemDeleteButton.setOnClickListener {
            deleteEvent(current)
        }
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount() = events.size

    private fun datesToTimeUntilString(futureDate: LocalDateTime, currentDate: LocalDateTime): String {
        if (currentDate.isAfter(futureDate)) {
            return "This has already happened"
        }

        val dateDifference = Duration.between(currentDate, futureDate)
        val daysAway = dateDifference.toDays()
        val hoursAway = dateDifference.toHours() % 24
        val minutesAway = dateDifference.toMinutes() % 60

        return "$daysAway days, $hoursAway hours and $minutesAway minutes"
    }
}