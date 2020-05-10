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
import kotlinx.android.synthetic.main.item_home_bill.view.*

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
        var mContext = context

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.d("ChatListAdapter", "Click")
        }

        fun bindGroup(utilityAccountItem: UtilityAccount) {
            this.utilityAccountItem = utilityAccountItem

            view.item_bill_name.text = utilityAccountItem.accountName
            view.item_bill_price.text = "KSH ${utilityAccountItem.amount.toInt().toString()}"
        }
    }
}