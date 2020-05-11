package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class AddBillModel : BaseObservable() {
    val accountName = ObservableField<String>()
    val accountAmount = ObservableField<String>()
    val phoneNumber=ObservableField<String>()
}