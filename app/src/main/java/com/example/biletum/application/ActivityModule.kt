package com.example.biletum.application


import com.example.biletum.activity.*
import com.example.biletum.profile.ProfileViewModel
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


}