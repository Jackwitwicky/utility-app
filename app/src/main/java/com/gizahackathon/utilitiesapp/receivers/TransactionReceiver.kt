package com.gizahackathon.utilitiesapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity
import com.gizahackathon.utilitiesapp.ui.successbill.SuccessBillActivity
import timber.log.Timber

class TransactionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var status = intent!!.getStringExtra("status")
        Timber.d("The status is: $status")

        openActivity(context!!, intent)
    }

    private fun openActivity(context: Context,receivedIntent: Intent) {
        var i = Intent(receivedIntent);
        i.setClass(context, SuccessBillActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        i.putExtra("UUID", receivedIntent.getStringExtra("uuid")
        context.startActivity(i);
    }
}