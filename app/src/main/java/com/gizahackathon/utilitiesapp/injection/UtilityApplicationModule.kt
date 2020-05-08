package com.gizahackathon.utilitiesapp.injection

import com.gizahackathon.utilitiesapp.ui.addbill.AddBillDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UtilityApplicationModule {
    @ContributesAndroidInjector
    fun contributeAddBillDialogFragmentInjector(): AddBillDialogFragment
}