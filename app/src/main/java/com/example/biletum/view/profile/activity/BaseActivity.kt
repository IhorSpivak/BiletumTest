package com.example.biletum.view.profile.activity


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    /**
     * Instance of [DispatchingAndroidInjector], used for injecting dependencies
     * in Android framework classes
     */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /**
     * Instance of [ViewModelProvider.Factory], used for
     * provide [ViewModel]s for activities
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Method for provide instances of [ViewModel]
     *
     * @param vmClass - java class of requested [ViewModel]
     *
     * @return instance of requested [ViewModel]
     */
    fun <T: ViewModel> getViewModel(vmClass: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory)
            .get(vmClass)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}