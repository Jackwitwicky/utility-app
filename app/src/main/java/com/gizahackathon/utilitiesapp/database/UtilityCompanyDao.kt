package com.gizahackathon.utilitiesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gizahackathon.utilitiesapp.domain.UtilityCompany

@Dao
interface UtilityCompanyDao {
    @Query("SELECT * from utilitycompany")
    fun getUtilityCompanies(): LiveData<List<UtilityCompany>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(utilityCompany: UtilityCompany): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(utilityCompanies: List<UtilityCompany>): List<Long>
}