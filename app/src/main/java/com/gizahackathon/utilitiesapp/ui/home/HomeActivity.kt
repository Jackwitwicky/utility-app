package com.gizahackathon.utilitiesapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.adapter.HomeAdapter
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject

const val CONFIRM_PAYMENT_REQUEST_CODE = 0

class HomeActivity : AppCompatActivity(), HomeAdapter.ItemSelectionListener {

    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
        if (requestCode == CONFIRM_PAYMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Timber.d("Confirm payment")
        }
    }

    override fun onPressPay(utilityAccount: UtilityAccount) {
        PaymentConfirmationDialog.newInstance(
            utilityAccount.amount.setScale(2).toString(), utilityAccount.accountName
        ).show(supportFragmentManager, "Tag_ConfirmPaymentDialog")
    }
}
