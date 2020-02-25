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
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class AddEventFragment: BaseFragment(R.layout.fragment_add_event) {


    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var  confirmationId: String = ""


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

        btn_login.setOnClickListener {
            var eventAddRequest : EventAddRequest(1,"test")
            viewModel.addEvent(eventAddRequest)
        }

        btn_confirm.setOnClickListener {
            viewModel.loginConfirm(confirmationId, ed_phone.text.toString())
        }
    }

    private fun handleNotConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleConfirmSuccess(token: String) {
        sharedPreferences.edit().putString(USER_KEY, token).apply()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())

        val snack = Snackbar.make(btn_login, Html.fromHtml("<font color=\"#F8941E\">Вы авторизовались</font>"), Snackbar.LENGTH_LONG)
        snack.show()
    }


    private fun handleNotAddEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleAddEventSuccess() {


    }



}
