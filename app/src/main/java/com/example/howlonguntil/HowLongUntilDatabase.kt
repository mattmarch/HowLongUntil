package com.example.howlonguntil

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.howlonguntil.entities.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Database(entities = [Event::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class HowLongUntilDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: HowLongUntilDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HowLongUntilDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context.applicationContext, HowLongUntilDatabase::class.java, "how_long_until_database")
                    .addCallback(HowLongUntilDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class HowLongUntilDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateInitialValues(database.eventDao())
                }
            }
        }

        suspend fun populateInitialValues(eventDao: EventDao) {
            eventDao.insert(Event("🎃 Halloween", LocalDateTime.of(2020, 10, 31, 0, 0)))
            eventDao.insert(Event("🎄 Christmas", LocalDateTime.of(2020, 12, 25, 0, 0)))
        }

    }
}