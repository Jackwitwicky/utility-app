package com.gizahackathon.utilitiesapp.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gizahackathon.utilitiesapp.domain.UtilityAccount

@Dao
interface UtilityAccountDao {

    @Query("SELECT * FROM utilityaccount WHERE utility_account_id=:utilityAccountID")
    fun getUtilityAccount(utilityAccountID: Long): UtilityAccount

    @Query("SELECT * FROM UtilityAccount")
    fun getUtilityAccounts(): DataSource.Factory<Int, UtilityAccount>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(utilityAccount: UtilityAccount): Long
}