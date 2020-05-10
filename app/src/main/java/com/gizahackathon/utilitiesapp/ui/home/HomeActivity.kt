package com.gizahackathon.utilitiesapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

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
        val homeListAdapter = HomeAdapter(this)
        home_recycler_view.apply {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        //populate the recycler view
        homeViewModel.allUtilityAccounts.observe(this, Observer {
            homeListAdapter.submitList(it)
        })
    }

    override fun onPressPay(utilityAccount: UtilityAccount) {
        // place hover implementation to pass amount
    }
}
