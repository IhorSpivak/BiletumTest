package com.example.biletum.view.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.View
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.biletum.application.Injectable
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


abstract class BaseFragment(@LayoutRes layoutId: Int): Fragment(layoutId), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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

    /**
     * Method for provide instances of [ViewModel] in context of Fragment's Activity
     *
     * @param vmClass - java class of requested [ViewModel]
     *
     * @return instance of requested [ViewModel]
     */
    fun <T: ViewModel> getActivityViewModel(vmClass: Class<T>): T {
        return ViewModelProviders.of(activity!!, viewModelFactory)
            .get(vmClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsetListener(view)
    }

    val navController: NavController by lazy (LazyThreadSafetyMode.NONE) {
        this.findNavController()
    }

    private fun setupInsetListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            val top = insets.systemWindowInsetTop
            val bottom = insets.systemWindowInsetBottom
            val left = insets.systemWindowInsetLeft
            val right = insets.systemWindowInsetRight

            handleInsets(left, top, right, bottom)

            insets
        }
        view.requestApplyInsets()
    }

    protected open fun handleInsets(left: Int, top: Int, right: Int, bottom: Int) {
        view?.apply {

        }
    }

    fun showSnackBarIntentData(view: View, context: Context, data: Intent) {
        Snackbar.make(
            view,
            Html.fromHtml("<font color=\"#78E5B4\">Изминение сохранено</font>"),
            Snackbar.LENGTH_LONG
        ).apply {
            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(80, 0, 80, 80)
            params.gravity = Gravity.BOTTOM
            params.anchorGravity = Gravity.BOTTOM

            view.layoutParams = params
            view.background = context.resources.getDrawable(com.example.biletum.R.drawable.snackbar_background, null)
            show()
        }
    }




}


