package com.gizahackathon.utilitiesapp.ui.addbill

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddBillViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddBillViewModel(mApplication) as T
    }
}