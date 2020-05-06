package com.gizahackathon.utilitiesapp.ui.addutility

import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
class AddUtilityDialogFragment : DialogFragment() {

    private lateinit var addUtilityViewModel : AddUtilityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_add_utility_dialog, container, false)
    }

    private fun setupViewModel() {
        val factory = AddUtilityViewModelFactory(activity!!.application)
        addUtilityViewModel = ViewModelProviders.of(this, factory).get(AddUtilityViewModel::class.java)
        addUtilityViewModel.getUtilityName()
        addUtilityViewModel.utilityName.observe(viewLifecycleOwner, Observer<String>{ utilityName ->
            // update UI
            Timber.d("The utility name is: $utilityName")
        })
    }
}
