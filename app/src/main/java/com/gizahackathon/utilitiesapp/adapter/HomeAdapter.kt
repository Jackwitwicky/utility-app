package com.gizahackathon.utilitiesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.databinding.ItemHomeBillBinding
import com.gizahackathon.utilitiesapp.domain.UtilityAccount
import timber.log.Timber

class HomeAdapter(private val itemSelectionListener: ItemSelectionListener) :
    PagedListAdapter<UtilityAccount, HomeAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {

    interface ItemSelectionListener {

        fun onPressPay(utilityAccount: UtilityAccount)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UtilityAccount>() {

            override fun areItemsTheSame(
                oldItem: UtilityAccount,
                newItem: UtilityAccount
            ): Boolean {
                return oldItem.utilityAccountId == newItem.utilityAccountId
            }

            override fun areContentsTheSame(
                oldItem: UtilityAccount,
                newItem: UtilityAccount
            ): Boolean {
                return newItem.utilityAccountId == oldItem.utilityAccountId
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBillBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val utilityAccount = getItem(position)
        holder.bindTo(utilityAccount, View.OnClickListener {
            Timber.d("Category selected selected %s", utilityAccount?.utilityCategoryId)
            utilityAccount?.let { itemSelectionListener.onPressPay(utilityAccount) }
        })
    }

    class ViewHolder(private val binding: ItemHomeBillBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(
            currentUtilityAccount: UtilityAccount?,
            onSelectedListener: View.OnClickListener
        ) {
            binding.apply {
                utilityBillItem = currentUtilityAccount
                onClickListener = onSelectedListener
                itemBillName.text = currentUtilityAccount!!.accountName
                itemPhoneNumber.text = currentUtilityAccount.phoneNumber.toString()

                itemBillPrice.text = itemView.context.resources.getString(
                    R.string.ksh_value_holder,
                    currentUtilityAccount.amount.toInt()
                )

                executePendingBindings()
            }

        }
    }
}