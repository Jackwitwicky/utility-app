package com.gizahackathon.utilitiesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import com.gizahackathon.utilitiesapp.page.Page
import com.gizahackathon.utilitiesapp.repository.UtilityAccountRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCompanyRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val utilityCompanyRepository: UtilityCompanyRepository,
    private val utilityAccountRepository: UtilityAccountRepository
) :
    ViewModel() {

    private val _utilityId = MutableLiveData<Long>()
    lateinit var utilityCategories: LiveData<List<UtilityCategory>>
    var utilityCompanies: LiveData<List<UtilityCompany>>

    init {
        utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
    }

    val allUtilityAccounts: LiveData<PagedList<UtilityAccount>> =
        utilityAccountRepository.getAllUtilityAccounts()
            .toLiveData(pageSize = Page.DEFAULT_PAGE_SIZE)

    fun getUtilityCompanies() {
        viewModelScope.launch {
            utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
        }
    }
}