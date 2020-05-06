package com.gizahackathon.utilitiesapp.ui.addutility

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddUtilityViewModel(application: Application) : AndroidViewModel(application) {

    var utilityName = MutableLiveData<String>()

    fun getUtilityName() {
        viewModelScope.launch {
//            userRepository.getUserData(userDataRequest, {i -> userResponse.value = i})
            utilityName = MutableLiveData("Prepaid");
        }
    }
}