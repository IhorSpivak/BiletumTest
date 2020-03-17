package com.example.biletum.application


import com.example.biletum.view.profile.events.MainActivity
import com.example.biletum.view.profile.events.event_add.AddEventActivity
import com.example.biletum.view.profile.events.event_info.EventInfoActivity
import com.example.biletum.view.profile.events.filter.EventsFilterActivity
import com.example.biletum.view.profile.login.CountryListActivity
import com.example.biletum.view.profile.login.LoginActivity
import com.example.biletum.view.profile.profile.ProfileActivity
import com.example.biletum.view.profile.start_splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {


    @ContributesAndroidInjector
    fun mainActivity(): MainActivity


    @ContributesAndroidInjector
    fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    fun addEventActivity(): AddEventActivity

    @ContributesAndroidInjector
    fun profileActivity(): ProfileActivity


    @ContributesAndroidInjector
    fun countryListActivity(): CountryListActivity

    @ContributesAndroidInjector
    fun filterEventsActivity(): EventsFilterActivity

    @ContributesAndroidInjector
    fun eventInfoActivity(): EventInfoActivity

    @ContributesAndroidInjector
    fun splashActivity(): SplashActivity


}