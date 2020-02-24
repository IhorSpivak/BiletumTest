package com.example.biletum.application

import android.content.Context
import com.yaroslavtupalo.knocs.application.MapperModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,ApplicationModule::class, ViewModelModule::class, ActivityModule::class,
    FragmentsModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance fun context(context: Context): Builder
        fun build(): ApplicationComponent

    }

    fun inject(app: AndroidApplication)

}