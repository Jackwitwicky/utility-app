package com.gizahackathon.utilitiesapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillDialogFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        add_bill_fab.setOnClickListener {
//            val addBillDialogFragment =
//                AddBillDialogFragment()
//            addBillDialogFragment.show(supportFragmentManager, "add_bill")

            startActivity(Intent(this, AddBillActivity::class.java))
        }
    }
}
