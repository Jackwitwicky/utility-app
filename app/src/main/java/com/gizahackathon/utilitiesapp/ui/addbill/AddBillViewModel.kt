package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import com.gizahackathon.utilitiesapp.repository.UtilityAccountRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCompanyRepository
import kotlinx.coroutines.launch

class AddBillViewModel(private val utilityCategoryRepository: UtilityCategoryRepository,
                       private val utilityAccountRepository: UtilityAccountRepository,
                       private val utilityCompanyRepository: UtilityCompanyRepository) :
    ViewModel() {

    var utilityAccountID = MutableLiveData<Long>()

    private val _utilityId = MutableLiveData<Long>()
    lateinit var utilityCategories :LiveData<List<UtilityCategory>>
    var utilityCompanies : LiveData<List<UtilityCompany>>

    init {
        utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
    }

    fun saveUtilityAccount(utilityAccount: UtilityAccount) {
        viewModelScope.launch {
            utilityAccountID.value = utilityAccountRepository.save(utilityAccount)
        }
    }

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

    fun getUtilityCategories() {
        viewModelScope.launch {
            utilityCategories = utilityCategoryRepository.getUtilityCategories()
        }
    }

    fun getUtilityCompanies() {
        viewModelScope.launch {
            utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
        }
    }
}