package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.repository.UtilityAccountRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import javax.inject.Inject

class AddBillViewModelFactory @Inject constructor(private val utilityCategoryRepository: UtilityCategoryRepository, private val utilityAccountRepository: UtilityAccountRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddBillViewModel(utilityCategoryRepository, utilityAccountRepository) as T
    }
}