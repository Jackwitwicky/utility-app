package com.gizahackathon.utilitiesapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillDialogFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        add_kplc_button.setOnClickListener {
            val addUtilityDialogFragment =
                AddBillDialogFragment()
            addUtilityDialogFragment.show(supportFragmentManager, "add_utility")
        }
    }
}
