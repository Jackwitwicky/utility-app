package com.gizahackathon.utilitiesapp.ui.home

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.databinding.DialogPaymentConfirmationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PaymentConfirmationDialog : DialogFragment() {

    companion object {
        private const val AMOUNT_TO_BE_PAID = "AMOUNT_TO_BE_PAID"
        private const val COMPANY_TO_PAY_TO = "COMPANY_TO_PAY_TO"
        fun newInstance(
            amount: String,
            accountName: String
        ): PaymentConfirmationDialog {
            val bundle: Bundle = bundleOf(
                AMOUNT_TO_BE_PAID to amount,
                COMPANY_TO_PAY_TO to accountName
            )
            return PaymentConfirmationDialog().apply { arguments = bundle }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val toAmount =
            requireArguments().getString(AMOUNT_TO_BE_PAID)
        val toAccountName =
            requireArguments().getString(COMPANY_TO_PAY_TO)

        val binding =
            DialogPaymentConfirmationBinding.inflate(LayoutInflater.from(requireContext()))

        binding.amount =
            getString(R.string.confirm_amount_dialog_string, toAmount.toString(), toAccountName)

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setPositiveButton(R.string.confirmation_continue) { _, _ ->
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, null)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
            }
            .create()

    }
}