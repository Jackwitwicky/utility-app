package com.gizahackathon.utilitiesapp.ui.addbill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_add_bill.*
import timber.log.Timber
import javax.inject.Inject

class AddBillActivity : AppCompatActivity() {

    private lateinit var addBillViewModel: AddBillViewModel

    @Inject
    lateinit var addBillViewModelFactory: AddBillViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bill)
        setupViewModel()

        add_bill_save_button.setOnClickListener {
            var utilityAccount = UtilityAccount(0,1L, 1L, "My Bill", 5000L.toBigDecimal())
            addBillViewModel.saveUtilityAccount(utilityAccount)
        }
    }

    private fun setupViewModel() {
        addBillViewModel =
            ViewModelProvider(this, addBillViewModelFactory).get(AddBillViewModel::class.java)
        addBillViewModel.utilityAccountID.observe(this, Observer { utilityAccountID ->
            Timber.d("The account has been saved with the id of $utilityAccountID")
        })
    }
}
