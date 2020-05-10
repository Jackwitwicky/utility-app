package com.gizahackathon.utilitiesapp.ui.successbill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_success_bill.*

class SuccessBillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_bill)

        success_go_back_button.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }
}
