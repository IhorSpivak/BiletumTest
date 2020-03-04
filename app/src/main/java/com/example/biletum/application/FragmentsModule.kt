package com.example.biletum.application


import com.example.biletum.events.AddEventFragment
import com.example.biletum.events.EventsFragment
import com.example.biletum.login.LoginFragment
import com.example.biletum.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsModule {



    @ContributesAndroidInjector
    fun addEventFragment(): AddEventFragment

    @ContributesAndroidInjector
    fun loginFragment():LoginFragment

    @ContributesAndroidInjector
    fun eventsFragment():EventsFragment

    @ContributesAndroidInjector
    fun profileFragment():ProfileFragment
}
