package com.example.biletum.application


import com.example.biletum.view.profile.events.event_add.AddEventFragmentStep1
import com.example.biletum.view.profile.events.events_list.EventsFragment
import com.example.biletum.view.profile.login.LoginFragment
import com.example.biletum.view.profile.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsModule {



    @ContributesAndroidInjector
    fun addEventFragment(): AddEventFragmentStep1

    @ContributesAndroidInjector
    fun loginFragment():LoginFragment

    @ContributesAndroidInjector
    fun eventsFragment(): EventsFragment

    @ContributesAndroidInjector
    fun profileFragment(): ProfileFragment
}
