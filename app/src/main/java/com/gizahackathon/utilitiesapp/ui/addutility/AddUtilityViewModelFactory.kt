package com.gizahackathon.utilitiesapp.ui.addutility

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddUtilityViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddUtilityViewModel(mApplication) as T
    }
}