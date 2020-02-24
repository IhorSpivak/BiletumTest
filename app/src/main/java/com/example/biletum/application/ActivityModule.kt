package com.example.biletum.application


import com.example.biletum.activity.LoginActivity
import com.example.biletum.activity.MainActivity
import com.example.biletum.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {


    @ContributesAndroidInjector
    fun mainActivity(): MainActivity


}