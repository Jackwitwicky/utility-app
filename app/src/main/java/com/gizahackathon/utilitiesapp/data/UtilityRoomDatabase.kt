package com.gizahackathon.utilitiesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Utility::class), version = 1, exportSchema = false)
abstract class UtilityRoomDatabase : RoomDatabase() {

    abstract fun utilityDao(): UtilityDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UtilityRoomDatabase? = null

        fun getDatabase(context: Context): UtilityRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UtilityRoomDatabase::class.java,
                    "utility_app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}