package com.gizahackathon.utilitiesapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton


@Module
class AppDatabaseModule constructor(context: Context, test: Boolean = false) {

    val POPULATE_UTILITY_COMPANIES = listOf(
        UtilityCompany(1L, "KPLC PREPAID", 888880),
        UtilityCompany(2L, "KPLC POSTPAID", 888888),
        UtilityCompany(3L, "ZUKU", 320323),
        UtilityCompany(4L, "GO TV", 423655),
        UtilityCompany(5L, "START TIMES TV", 585858),
        UtilityCompany(6L, "NAIROBI WATER", 444400)
    )

    val POPULATE_UTILITY_CATEGORIES = listOf(
        UtilityCategory(1L, "PAYBILL"),
        UtilityCategory(2L, "TILL NUMBER"),
        UtilityCategory(3L, "PHONE NUMBER")
    )

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
