package com.gizahackathon.utilitiesapp.data

import androidx.lifecycle.LiveData

class UtilityRepository(private val utilityDao: UtilityDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Utility>> = utilityDao.getAllUtilities()

    suspend fun insert(utility: Utility) {
        utilityDao.insert(utility)
    }
}