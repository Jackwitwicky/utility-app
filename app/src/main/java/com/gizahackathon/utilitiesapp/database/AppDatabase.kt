package com.gizahackathon.utilitiesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gizahackathon.utilitiesapp.domain.*

/**
 * Application local database
 */
@Database(
    entities = [UtilityCategory::class, UtilityCompany::class, UtilityAccountNumber::class, UtilityAccount::class, UtilityTransaction::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun utilityCategoryDao(): UtilityCategoryDao
}