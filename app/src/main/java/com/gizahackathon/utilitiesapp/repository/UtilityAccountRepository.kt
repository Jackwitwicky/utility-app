package com.gizahackathon.utilitiesapp.repository

import androidx.paging.DataSource
import com.gizahackathon.utilitiesapp.database.UtilityAccountDao
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import javax.inject.Inject

class UtilityAccountRepository @Inject constructor(private val utilityAccountDao: UtilityAccountDao) {

    suspend fun save(utilityAccount: UtilityAccount): Long =
        utilityAccountDao.save(utilityAccount)

    fun getAllUtilityAccounts(): DataSource.Factory<Int, UtilityAccount> =
        utilityAccountDao.getUtilityAccounts()
}