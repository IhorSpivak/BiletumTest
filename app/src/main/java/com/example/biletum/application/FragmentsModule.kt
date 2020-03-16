package com.example.biletum.application


import com.example.biletum.view.profile.events.event_add.AddEventFragment
import com.example.biletum.view.profile.events.events_list.EventsFragment
import com.example.biletum.view.profile.login.LoginFragment
import com.example.biletum.view.profile.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsModule {



    @ContributesAndroidInjector
    fun addEventFragment(): AddEventFragment

    @ContributesAndroidInjector
    fun loginFragment():LoginFragment

    @ContributesAndroidInjector
    fun eventsFragment(): EventsFragment

    @ContributesAndroidInjector
    fun profileFragment(): ProfileFragment
}
