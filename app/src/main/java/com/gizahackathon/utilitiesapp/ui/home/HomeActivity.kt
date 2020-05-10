package com.gizahackathon.utilitiesapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.hover.sdk.api.Hover
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private var utilityAccountsList = ArrayList<UtilityAccount>()

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Hover.initialize(this)
        add_bill_fab.setOnClickListener {
//            val addBillDialogFragment =
//                AddBillDialogFragment()
//            addBillDialogFragment.show(supportFragmentManager, "add_bill")

            startActivity(Intent(this, AddBillActivity::class.java))
        }

        setupUtilityList()
        setupViewModel()
    }

    private fun setupViewModel() {
        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getUtilityAccounts()
        homeViewModel.utilityAccounts.observe(this, Observer { utilityAccounts ->
            Timber.d("The companies are: $utilityAccounts")
            utilityAccountsList = utilityAccounts as ArrayList<UtilityAccount>
            homeAdapter.updateData(utilityAccounts)
            Timber.d("The number of items are: ${homeAdapter.itemCount}")
        })
    }

    private fun setupUtilityList() {
        homeAdapter = HomeAdapter(utilityAccountsList, this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        home_recycler_view.layoutManager = layoutManager
        home_recycler_view.adapter = homeAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 0 && resultCode === Activity.RESULT_OK) {
            val sessionTextArr =
                data!!.getStringArrayExtra("session_messages")
            val uuid = data!!.getStringExtra("uuid")
            Timber.d("The session message is: $sessionTextArr and the UUID is $uuid")
        } else if (requestCode === 0 && resultCode === Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Error: " + data!!.getStringExtra("error"), Toast.LENGTH_LONG)
                .show()
        }
    }
}
