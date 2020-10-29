package com.example.howlonguntil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.howlonguntil.entities.Event

class EventListAdapter internal  constructor(context: Context):RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var events = emptyList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventItemTitle = itemView.findViewById<TextView>(R.id.eventTitle)
        val eventItemTimeUntil = itemView.findViewById<TextView>(R.id.timeUntil)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val eventView = inflater.inflate(R.layout.event_item, parent, false)
        return EventViewHolder(eventView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = events[position]
        holder.eventItemTitle.text = current.name
        holder.eventItemTimeUntil.text = current.date.toString()
    }

    internal fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    override fun getItemCount() = events.size
}