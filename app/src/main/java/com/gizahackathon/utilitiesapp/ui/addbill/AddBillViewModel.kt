package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.domain.UtilityCategory
import com.gizahackathon.utilitiesapp.domain.UtilityCompany
import com.gizahackathon.utilitiesapp.repository.UtilityAccountRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import com.gizahackathon.utilitiesapp.repository.UtilityCompanyRepository
import kotlinx.coroutines.launch
import java.math.BigDecimal

class AddBillViewModel(
    private val utilityCategoryRepository: UtilityCategoryRepository,
    private val utilityAccountRepository: UtilityAccountRepository,
    private val utilityCompanyRepository: UtilityCompanyRepository
) :
    ViewModel() {

    var utilityAccountID = MutableLiveData<Long>()
    lateinit var utilityCategories: LiveData<List<UtilityCategory>>
    var utilityCompanies: LiveData<List<UtilityCompany>>
    val addBill = AddBillModel()
    var utilityCategoryId: Long? = null
    var utilityCompanyId: Long? = null

    init {
        utilityCompanies = utilityCompanyRepository.getUtilityCompanies()
    }

    fun validateFormAndSave(): ValidationResult {
        val accountName = addBill.accountName.get()
        val amount = addBill.accountAmount.get()
        return if (accountName.isNullOrBlank()) {
            ValidationResult(accountNameError = R.string.add_bill_account_name_require)
        } else if (amount.isNullOrBlank()) {
            ValidationResult(accountAmountError = R.string.add_bill_account_amount_require)
        } else {
            addBill(utilityCategoryId!!, utilityCompanyId!!, accountName, amount.toBigDecimal())
            ValidationResult(isDataValid = true)
        }

    }

    private fun addBill(
        utilityCategoryId: Long,
        utilityCompanyId: Long,
        accountName: String,
        amount: BigDecimal
    ) {
        viewModelScope.launch {
            val utilityAccount = UtilityAccount(
                utilityCategoryId = utilityCategoryId,
                utilityCompanyId = utilityCompanyId,
                accountName = accountName,
                amount = amount
            )
            utilityAccountRepository.save(utilityAccount)
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