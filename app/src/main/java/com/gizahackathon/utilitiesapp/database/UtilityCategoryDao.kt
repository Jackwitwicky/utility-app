package com.gizahackathon.utilitiesapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gizahackathon.utilitiesapp.domain.UtilityCategory

@Dao
interface UtilityCategoryDao {
    @Query("SELECT * from utilitycategory")
    fun getUtilityCategories(): LiveData<List<UtilityCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(utilityCategory: UtilityCategory): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(utilityCategories: List<UtilityCategory>): List<Long>
}