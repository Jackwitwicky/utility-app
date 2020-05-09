package com.gizahackathon.utilitiesapp.ui.addbill

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.hover.sdk.api.Hover
import com.hover.sdk.api.HoverParameters
import dagger.android.AndroidInjection
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

        Hover.initialize(this);
    }

    private fun setupViewModel() {
        addBillViewModel =
            ViewModelProvider(this, addBillViewModelFactory).get(AddBillViewModel::class.java)
        addBillViewModel.utilityAccountID.observe(this, Observer { utilityAccountID ->
            Timber.d("The account has been saved with the id of $utilityAccountID")
        })
    }
}
