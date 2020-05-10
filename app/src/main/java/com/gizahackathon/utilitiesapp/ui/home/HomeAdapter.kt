package com.gizahackathon.utilitiesapp.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import com.hover.sdk.api.HoverParameters
import kotlinx.android.synthetic.main.item_home_bill.view.*
import timber.log.Timber

class HomeAdapter(private var utilityAccountList: List<UtilityAccount>, private val context: Activity) : RecyclerView.Adapter<HomeAdapter.UtilityListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilityListHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_bill, parent, false)

        return UtilityListHolder(v, context)
    }

    override fun getItemCount(): Int {
        return utilityAccountList.size
    }

    fun updateData(utilityAccounts: List<UtilityAccount>) {
        this.utilityAccountList = utilityAccounts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UtilityListHolder, position: Int) {
        val groupItem = utilityAccountList[position]
        holder.bindGroup(groupItem)
    }

    class UtilityListHolder(v: View, context: Context) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private  var view: View = v
        private var utilityAccountItem: UtilityAccount? = null
        var mContext = context as Activity

        init {
//            v.setOnClickListener(this)
            view.item_bill_pay.setOnClickListener(this)
        }

        override fun onClick(itemView: View?) {
            Timber.d("The bill name is: ${utilityAccountItem!!.accountName}")
            var hoverIntent = HoverParameters.Builder(mContext)
                .request("0380a0cf")
                .buildIntent()
            mContext.startActivityForResult(hoverIntent, 0)
        }

        fun bindGroup(utilityAccountItem: UtilityAccount) {
            this.utilityAccountItem = utilityAccountItem

            view.item_bill_name.text = utilityAccountItem.accountName
            view.item_bill_price.text = "KSH ${utilityAccountItem.amount.toInt().toString()}"
        }
    }
}