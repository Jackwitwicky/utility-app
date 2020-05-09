package com.gizahackathon.utilitiesapp.injection

import com.gizahackathon.utilitiesapp.ui.addbill.AddBillActivity
import com.gizahackathon.utilitiesapp.ui.addbill.AddBillDialogFragment
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UtilityApplicationModule {
    @ContributesAndroidInjector
    fun contributeAddBillDialogFragmentInjector(): AddBillDialogFragment

    @ContributesAndroidInjector
    fun contributeAddBillActivityInjector(): AddBillActivity

    @ContributesAndroidInjector
    fun contributeHomeActivityInjector(): HomeActivity
}