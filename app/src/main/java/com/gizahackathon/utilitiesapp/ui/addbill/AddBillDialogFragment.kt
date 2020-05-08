package com.gizahackathon.utilitiesapp.ui.addbill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.gizahackathon.utilitiesapp.R
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class AddBillDialogFragment : DialogFragment() {

    private lateinit var addBillViewModel : AddBillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_add_bill_dialog, container, false)
    }

    private fun setupViewModel() {
        val factory = AddBillViewModelFactory(activity!!.application)
        addBillViewModel = ViewModelProviders.of(this, factory).get(AddBillViewModel::class.java)
        addBillViewModel.getUtilityName()
        addBillViewModel.utilityName.observe(viewLifecycleOwner, Observer<String>{ utilityName ->
            // update UI
            Timber.d("The utility name is: $utilityName")
        })
    }
}
