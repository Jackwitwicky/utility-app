package com.gizahackathon.utilitiesapp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.gizahackathon.utilitiesapp.database.AppDatabaseModule
import com.gizahackathon.utilitiesapp.injection.DaggerUtilityComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class UtilityApp : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        // set up dagger android injection
        val component = DaggerUtilityComponent.builder()
            .appDatabaseModule(AppDatabaseModule(this))
            .build()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}