package com.gizahackathon.utilitiesapp.ui.addbill

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.R
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
        hideAmountEditText()
        binding.addBill = addBillViewModel.addBill
        // clear errors after text change
        val textWatcher: (textView: TextView) -> Unit = { it.error = null }
        listOf(binding.addBillName, binding.addBillAmount)
            .forEach { it.addTextChangedListener(textWatcher) }
        // display drop downs for company
        displayUtilityCategoryDropDown()
        displayUtilityCompanyDropDown()
    }

    private fun hideAmountEditText() {
        binding.addBillPaymentTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.fixedAmount = false
            when (checkedId) {
                R.id.yes_selected -> {
                    binding.fixedAmount = false
                }
                R.id.no_selected -> {
                    binding.fixedAmount = true
                }
            }
        }
    }

    private fun setupViewModel() {
        addBillViewModel =
            ViewModelProvider(this, addBillViewModelFactory).get(AddBillViewModel::class.java)
        addBillViewModel.utilityAccountID.observe(this, Observer { utilityAccountID ->
            Timber.d("The account has been saved with the id of $utilityAccountID")
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

    private fun displayUtilityCategoryDropDown() {
        addBillViewModel.getUtilityCategories()
        addBillViewModel.utilityCategories.observe(this, Observer { utilityCategories ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                utilityCategories.map { it.utilityName }
            )
            binding.addUtilityCategorySpinner.adapter = adapter

            binding.addUtilityCategorySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        utilityCategories.map {
                            binding.paybillVisible = position + 1.toLong() != 1L
                        }
                        addBillViewModel.utilityCategoryId = position + 1.toLong()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }
        })
    }

    private fun displayUtilityCompanyDropDown() {
        addBillViewModel.getUtilityCompanies()
        addBillViewModel.utilityCompanies.observe(this, Observer { utilityCompanies ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                utilityCompanies.map { it.companyName }
            )
            binding.addBillCompanySpinner.adapter = adapter

            binding.addBillCompanySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        addBillViewModel.utilityCompanyId = position + 1.toLong()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }

        })
    }

    private fun addWaterBill() {
        showValidationResult(addBillViewModel.validateFormAndSave())
    }
}
