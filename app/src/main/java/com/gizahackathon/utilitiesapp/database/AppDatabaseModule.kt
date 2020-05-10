package com.gizahackathon.utilitiesapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.*
import javax.inject.Singleton


@Module
class AppDatabaseModule constructor(context: Context, test: Boolean = false) {

    val POPULATE_UTILITY_COMPANIES = listOf(UtilityCompany(1L, "kplc_prepaid", 888880),
        UtilityCompany(2L, "kplc_postpaid", 888888),
        UtilityCompany(3L, "zuku", 320323),
        UtilityCompany(4L, "go_tv", 423655),
        UtilityCompany(5L, "star_times_tv", 585858),
        UtilityCompany(6L, "nairobi_water", 444400))

    val POPULATE_UTILITY_CATEGORIES = listOf(UtilityCategory(1L, "paybill"),
        UtilityCategory(2L, "till_number"),
        UtilityCategory(3L, "phone_number"))

    private val appDatabase: AppDatabase = if (test) {
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    } else {
        val rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                // ADD YOUR "Math - Sport - Art - Music" here
                GlobalScope.launch {
                    providesAppDatabase().utilityCompanyDao().saveAll(POPULATE_UTILITY_COMPANIES)
                    providesAppDatabase().utilityCategoryDao().saveAll(POPULATE_UTILITY_CATEGORIES)
                }
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                // do something every time database is open
            }
        }

        Room.databaseBuilder(context, AppDatabase::class.java, "utility_app_database")
            .fallbackToDestructiveMigration() // prevent app crash for missing old versions
            .addCallback(rdc)
            .build()
        //When version changes upgrade it -.addMigrations(MIGRATION_VERSION)
    }

    @Provides
    @Singleton
    fun providesAppDatabase() = appDatabase

    @Provides
    @Singleton
    fun providesCategoryDao() = appDatabase.utilityCategoryDao()

    @Provides
    @Singleton
    fun provideUtilityAccountDao() = appDatabase.utilityAccountDao()

    @Provides
    @Singleton
    fun provideUtilityCompanyDao() = appDatabase.utilityCompanyDao()
}
