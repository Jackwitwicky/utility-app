package com.gizahackathon.utilitiesapp.ui.addutility

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.gizahackathon.utilitiesapp.R

/**
 * A simple [Fragment] subclass.
 */
class AddUtilityDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_utility_dialog, container, false)
    }


}
