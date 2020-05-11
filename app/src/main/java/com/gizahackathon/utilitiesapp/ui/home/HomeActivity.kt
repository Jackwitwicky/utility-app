package com.gizahackathon.utilitiesapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.adapter.HomeAdapter
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.hover.sdk.api.Hover
import com.hover.sdk.api.HoverParameters
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject

const val CONFIRM_PAYMENT_REQUEST_CODE = 0
const val PAY_UTILITY_REQUEST_CODE = 1

class HomeActivity : AppCompatActivity(), HomeAdapter.ItemSelectionListener,
    PaymentConfirmationDialog.OnButtonClickedListener {

    private lateinit var homeViewModel: HomeViewModel
    private var amount: String? = null
    private var phoneNumber: String? = null


    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Hover.initialize(this)
        add_bill_fab.setOnClickListener {
            startActivity(Intent(this, AddBillActivity::class.java))
        }
        setupViewModel()
        setupUtilityList()
    }

    private fun setupViewModel() {
        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    private fun setupUtilityList() {
        //configure recycler view
        val homeListAdapter =
            HomeAdapter(this)
        home_recycler_view.apply {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        //populate the recycler view
        homeViewModel.allUtilityAccounts.observe(this, Observer {
            homeListAdapter.submitList(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PAY_UTILITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            val sessionTextArr =
                data!!.getStringArrayExtra("session_messages")
            val uuid = data.getStringExtra("uuid")
            Timber.d("The session message is: $sessionTextArr and the UUID is $uuid")
        } else if (requestCode === 0 && resultCode === Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Error: " + data!!.getStringExtra("error"), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onPressPay(utilityAccount: UtilityAccount) {
        amount = utilityAccount.amount.toString()
        phoneNumber = utilityAccount.phoneNumber.toString()
        PaymentConfirmationDialog.newInstance(
            utilityAccount.amount.setScale(2).toString(), utilityAccount.phoneNumber.toString()
        ).show(supportFragmentManager, "Tag_ConfirmPaymentDialog")

    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is PaymentConfirmationDialog) {
            fragment.setOnButtonClickedListener(this)
        }
    }


    override fun onConfirmationDialogButtonClicked(userOption: Int) {
        Timber.d("Phone Number %s", phoneNumber)
        Timber.d("Amount %s", amount)

        if (userOption == Activity.RESULT_OK) {
            Timber.d("Confirm payment")
            var hoverIntent = HoverParameters.Builder(this)
                .request("02dc81bc")
                .extra("PhoneNumber", phoneNumber)
                .extra("Amount", amount)
                .buildIntent()
            startActivityForResult(hoverIntent, PAY_UTILITY_REQUEST_CODE)
        } else {
            Timber.d("User cancelled")
        }
    }
}
