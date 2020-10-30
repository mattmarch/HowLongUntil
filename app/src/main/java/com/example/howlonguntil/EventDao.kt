package com.example.howlonguntil

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.howlonguntil.entities.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM Event ORDER BY eventDate")
    fun getAllEvents(): LiveData<List<Event>>

    @Insert
    suspend fun insert(event: Event)

}