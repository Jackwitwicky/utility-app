package com.gizahackathon.utilitiesapp.injection

import com.gizahackathon.utilitiesapp.UtilityApp
import com.gizahackathon.utilitiesapp.database.AppDatabaseModule
import com.gizahackathon.utilitiesapp.repository.UtilityCategoryRepository
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, UtilityApplicationModule::class, AppDatabaseModule::class])
interface UtilityComponent : AndroidInjector<UtilityApp> {
    fun utilityCategory(): UtilityCategoryRepository
}
