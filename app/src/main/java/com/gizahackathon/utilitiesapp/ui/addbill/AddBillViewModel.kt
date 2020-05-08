package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import kotlinx.coroutines.launch

class AddBillViewModel(private val utilityCategoryRepository: UtilityCategoryRepository) :
    ViewModel() {


    private val _utilityId = MutableLiveData<Long>()

    fun setUtilityId(internalId: Long) {
        _utilityId.value = internalId
    }

    fun saveUtility(utilityName: String) {
        viewModelScope.launch {
            val utilityCategory = UtilityCategory(
                utilityName = utilityName
            )
            utilityCategoryRepository.save(utilityCategory)
        }

    }
}