package com.gizahackathon.utilitiesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gizahackathon.utilitiesapp.domain.UtilityAccount

@Dao
interface UtilityAccountDao {

    @Query("SELECT * FROM utilityaccount WHERE utility_account_id=:utilityAccountID")
    fun getUtilityAccount(utilityAccountID: Long) : UtilityAccount

    @Query("SELECT * from utilityaccount")
    fun getUtilityAccounts(): LiveData<List<UtilityAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(utilityAccount: UtilityAccount): Long
}