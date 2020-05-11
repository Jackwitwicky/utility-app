package com.gizahackathon.utilitiesapp.repository

import androidx.lifecycle.LiveData
import com.gizahackathon.utilitiesapp.database.UtilityCompanyDao
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import javax.inject.Inject

class UtilityCompanyRepository @Inject constructor(private val utilityCompanyDao: UtilityCompanyDao) {

    suspend fun save(utilityCategory: UtilityCompany): Long =
        utilityCompanyDao.save(utilityCategory)

    fun getUtilityCompanies(): LiveData<List<UtilityCompany>> =
        utilityCompanyDao.getUtilityCompanies()
}