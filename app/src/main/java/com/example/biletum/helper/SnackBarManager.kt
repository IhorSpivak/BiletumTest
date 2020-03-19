package com.example.biletum.helper

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_event1.*

object SnackBarManager {



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