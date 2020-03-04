package com.example.biletum.events


import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.activity.MainActivity
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.fragments.BaseFragment
import com.example.biletum.helper.USER_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class AddEventFragment: BaseFragment(R.layout.fragment_add_event) {


    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences



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

        btn_add_event.setOnClickListener {
            viewModel.addEvent(sharedPreferences.getString(USER_KEY,"").toString(),
                EventAddRequest("test","test","test","2020-04-01T15:45","2020-04-01T15:55","test",
                "test","test","test","test","test","test"
            ))
        }


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
