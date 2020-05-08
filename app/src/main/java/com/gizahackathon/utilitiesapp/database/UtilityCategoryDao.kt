package com.gizahackathon.utilitiesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gizahackathon.utilitiesapp.domain.UtilityCategory

@Dao
interface UtilityCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(utilityCategory: UtilityCategory): Long
}