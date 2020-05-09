package com.gizahackathon.utilitiesapp.ui.addbill

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.databinding.ActivityAddBillBinding
import com.gizahackathon.utilitiesapp.extension.addTextChangedListener
import com.gizahackathon.utilitiesapp.extension.setStringError
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject


class AddBillActivity : AppCompatActivity() {

    private lateinit var addBillViewModel: AddBillViewModel
    private lateinit var binding: ActivityAddBillBinding

    @Inject
    lateinit var addBillViewModelFactory: AddBillViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewModel()
        binding.addBillSaveButton.setOnClickListener {
            addWaterBill()
        }

        binding.addBill = addBillViewModel.addBill

        // clear errors after text change
        val textWatcher: (textView: TextView) -> Unit = { it.error = null }
        listOf(binding.addBillName, binding.addBillAmount)
            .forEach { it.addTextChangedListener(textWatcher) }
    }

    private fun setupViewModel() {
        addBillViewModel =
            ViewModelProvider(this, addBillViewModelFactory).get(AddBillViewModel::class.java)
        addBillViewModel.utilityAccountID.observe(this, Observer { utilityAccountID ->
            Timber.d("The account has been saved with the id of $utilityAccountID")
        })

        addBillViewModel.getUtilityCompanies()
        addBillViewModel.utilityCompanies.observe(this, Observer { utilityCompanies ->
            Timber.d("The companies are: $utilityCompanies")
        })
    }

    private fun showValidationResult(validationResult: ValidationResult) {
        binding.addBillName.setStringError(validationResult.accountNameError)
        binding.addBillAmount.setStringError(validationResult.accountAmountError)
        when {
            validationResult.accountNameError != null -> binding.addBillName.requestFocus()
            validationResult.accountAmountError != null -> binding.addBillAmount.requestFocus()
        }
    }

    private fun addWaterBill() {
        showValidationResult(addBillViewModel.validateFormAndSave())
    }
}
