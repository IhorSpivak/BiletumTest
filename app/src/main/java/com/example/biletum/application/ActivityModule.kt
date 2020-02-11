package com.example.biletum.application


import com.example.biletum.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {



    @ContributesAndroidInjector
    fun mainActivity(): MainActivity


}