package com.example.biletum.application

import android.app.Activity
import android.app.Application
import com.example.biletum.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import com.google.android.gms.analytics.GoogleAnalytics
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.android.gms.analytics.Tracker
import android.R
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class AndroidApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var sAnalytics: GoogleAnalytics? = null
    private var sTracker: Tracker? = null


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        sAnalytics = GoogleAnalytics.getInstance(this);
        initPrettyLogger()

    }



    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector


    private fun initPrettyLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(5)
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(
                priority: Int,
                tag: String?
            ): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}