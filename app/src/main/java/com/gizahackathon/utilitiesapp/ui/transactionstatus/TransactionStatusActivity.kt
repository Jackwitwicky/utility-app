package com.gizahackathon.utilitiesapp.ui.transactionstatus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_transaction_status.*

class TransactionStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_status)

        val transactionStatus = intent.getStringExtra("status")
        updateUI(transactionStatus)

        transaction_status_go_back_button.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }

    private fun updateUI(transactionStatus: String) {
        if (transactionStatus == "succeeded") {
            // do nothing for now as default design is success state
        }
        else {
            transaction_status_icon.setImageResource(R.drawable.ic_fail_icon)
            transaction_status_header.text = getString(R.string.transaction_status_fail_header)
            transaction_status_message.text = getString(R.string.transaction_status_fail_message)
        }
    }
}
