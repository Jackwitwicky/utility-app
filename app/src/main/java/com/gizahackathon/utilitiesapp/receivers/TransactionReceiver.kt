package com.gizahackathon.utilitiesapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity
import timber.log.Timber

public class TransactionReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var status = intent!!.getStringExtra("status")
        Timber.d("The status is: $status")

        openActivity(context!!, intent)
    }

//    @Override
//    public void onReceive(Context context, Intent i) {
//        Transaction t = Transaction.getByUuid(i.getStringExtra("uuid"), context);
//        if (t != null) {
//            t.status = i.getStringExtra("status");
//            t.updateTimestamp = i.getLongExtra("update_timestamp", Utils.now());
//            t.save(context);
//        }
//        openActivity(context, i);
//    }

    private fun openActivity(context: Context,receivedIntent: Intent) {
        var i = Intent(receivedIntent);
        i.setClass(context, AddBillActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        i.putExtra("UUID", receivedIntent.getStringExtra("uuid")
        context.startActivity(i);
    }
}