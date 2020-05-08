package com.gizahackathon.utilitiesapp.repository

import androidx.lifecycle.LiveData
import com.gizahackathon.utilitiesapp.database.UtilityAccountDao
import com.gizahackathon.utilitiesapp.database.UtilityCategoryDao
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import javax.inject.Inject

class UtilityAccountRepository @Inject constructor(private val utilityAccountDao: UtilityAccountDao) {

    suspend fun save(utilityAccount: UtilityAccount): Long =
        utilityAccountDao.save(utilityAccount)

    fun getAllUtilityAccounts(): LiveData<List<UtilityAccount>> =
        utilityAccountDao.getUtilityAccounts()
}