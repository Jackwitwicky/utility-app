package com.gizahackathon.utilitiesapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.repository.UtilityAccountRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCompanyRepository
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillViewModel
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val utilityCompanyRepository: UtilityCompanyRepository,
                                               private val utilityAccountRepository: UtilityAccountRepository
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(utilityCompanyRepository, utilityAccountRepository) as T
    }
}