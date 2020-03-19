package com.example.biletum.application


import com.example.biletum.view.profile.events.event_add.AddEventFragmentStep1
import com.example.biletum.view.profile.events.event_add.AddEventFragmentStep2
import com.example.biletum.view.profile.events.event_add.AddEventFragmentStep3
import com.example.biletum.view.profile.events.event_add.AddEventFragmentStep4
import com.example.biletum.view.profile.events.events_list.EventsFragment
import com.example.biletum.view.profile.login.LoginFragment
import com.example.biletum.view.profile.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentsModule {



    @ContributesAndroidInjector
    fun addEventFragment1(): AddEventFragmentStep1


    @ContributesAndroidInjector
    fun addEventFragment2(): AddEventFragmentStep2


    @ContributesAndroidInjector
    fun addEventFragment3(): AddEventFragmentStep3


    @ContributesAndroidInjector
    fun addEventFragment4(): AddEventFragmentStep4

    @ContributesAndroidInjector
    fun loginFragment():LoginFragment

    @ContributesAndroidInjector
    fun eventsFragment(): EventsFragment

    @ContributesAndroidInjector
    fun profileFragment(): ProfileFragment
}
