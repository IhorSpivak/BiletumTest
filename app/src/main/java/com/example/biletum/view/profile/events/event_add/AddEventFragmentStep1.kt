package com.example.biletum.view.profile.events.event_add


import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.view_models.EventsViewModel
import com.example.biletum.view.profile.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import com.example.biletum.helper.DateHelper
import kotlinx.android.synthetic.main.fragment_add_event1.*
import java.util.*


class AddEventFragmentStep1: BaseFragment(com.example.biletum.R.layout.fragment_add_event1) {


    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var calendar : Calendar? = null
    private var dateEvent : String? = ""
    private var timeEvent : String? = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = getViewModel(EventsViewModel::class.java)
        viewModel.addEventData.observe(this, Observer {
            when (it.result) {
                true -> {
                    handleAddEventSuccess()
                }
                false -> {
                    handleNotAddEvent()
                }
            }

        })

        ed_start_day.setOnClickListener {
            onDepartureDateClick()
        }




    }

    fun onDepartureDateClick() {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.setTime(Date())
        val datePickerDialog = DatePickerDialog(
            context!!, -1, { view1, year, month, dayOfMonth ->
                calendar = Calendar.getInstance()
                calendar!!.set(Calendar.YEAR, year)
                calendar!!.set(Calendar.MONTH, month)
                calendar!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)


                val timePickerDialog = TimePickerDialog(
                    context,
                    { view11, hourOfDay, minute ->
                        calendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar!!.set(Calendar.MINUTE, minute)
                        ed_start_day.setText(DateHelper.getStringFromCalendarForOrder(calendar!!))

                        dateEvent = DateHelper.getDateForServerFromCalendar(calendar!!)
                        timeEvent = DateHelper.getTimeForServerFromCalendar(calendar!!)
                    },
                    currentCalendar.get(Calendar.HOUR_OF_DAY),
                    currentCalendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()

            },
            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()


    }


    private fun handleNotAddEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleAddEventSuccess() {

        val snack = Snackbar.make(btn_login, Html.fromHtml("<font color=\"#F8941E\">Ивент успешно добавлен</font>"), Snackbar.LENGTH_LONG)
        snack.show()
        activity!!.onBackPressed()
    }



}
