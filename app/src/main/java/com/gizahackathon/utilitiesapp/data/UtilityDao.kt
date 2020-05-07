package com.gizahackathon.utilitiesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UtilityDao {

    @Query("SELECT * from utility_table")
    fun getAllUtilities(): LiveData<List<Utility>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(utility: Utility)

    @Query("DELETE from utility_table")
    suspend fun deleteAllUtilities()

}