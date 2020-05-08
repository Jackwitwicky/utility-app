package com.gizahackathon.utilitiesapp.database

import android.content.Context
import androidx.room.Room
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule constructor(context: Context, test: Boolean = false) {

    private val appDatabase: AppDatabase = if (test) {
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    } else {
        Room.databaseBuilder(context, AppDatabase::class.java, "utility_app_database")
            .fallbackToDestructiveMigration() // prevent app crash for missing old versions
            .createFromAsset("databases/utility_app.db")
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
}
