package com.gizahackathon.utilitiesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gizahackathon.utilitiesapp.database.UtilityCategoryDao
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import javax.inject.Inject

class UtilityCategoryRepository @Inject constructor(private val utilityCategoryDao: UtilityCategoryDao) {

    suspend fun save(utilityCategory: UtilityCategory): Long =
        utilityCategoryDao.save(utilityCategory)

    fun getUtilityCategories(): LiveData<List<UtilityCategory>> =
        utilityCategoryDao.getUtilityCategories()
}