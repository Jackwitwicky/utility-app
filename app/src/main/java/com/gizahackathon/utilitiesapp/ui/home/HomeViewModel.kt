package com.gizahackathon.utilitiesapp.ui.home

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

class HomeViewModel(private val utilityCompanyRepository: UtilityCompanyRepository,
                    private val utilityAccountRepository: UtilityAccountRepository
) :
    ViewModel() {

    private val _utilityId = MutableLiveData<Long>()
    lateinit var utilityCategories : LiveData<List<UtilityCategory>>
    var utilityCompanies : LiveData<List<UtilityCompany>>
    var utilityAccounts : LiveData<List<UtilityAccount>>

    init {
        utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
        utilityAccounts = utilityAccountRepository.getAllUtilityAccounts()
    }

    fun getUtilityCompanies() {
        viewModelScope.launch {
            utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
        }
    }

    fun getUtilityAccounts() {
        viewModelScope.launch {
            utilityAccounts = utilityAccountRepository.getAllUtilityAccounts()
        }
    }
}