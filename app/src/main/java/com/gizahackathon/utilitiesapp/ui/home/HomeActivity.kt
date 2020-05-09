package com.gizahackathon.utilitiesapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillDialogFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var addBillViewModel: HomeViewModel

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        add_bill_fab.setOnClickListener {
//            val addBillDialogFragment =
//                AddBillDialogFragment()
//            addBillDialogFragment.show(supportFragmentManager, "add_bill")

            startActivity(Intent(this, AddBillActivity::class.java))
        }


        setupViewModel()
        setupUtilityList()
    }

    private fun setupViewModel() {
        addBillViewModel =
            ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        addBillViewModel.utilityCompanies.observe(this, Observer { utilityCompanies ->
            Timber.d("The companies are: $utilityCompanies")
        })
    }

    private fun setupUtilityList() {}
}
