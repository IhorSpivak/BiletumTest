package com.example.biletum.view.profile.events.event_add

import android.os.Bundle
import android.os.Handler
import com.example.biletum.R
import com.example.biletum.view.profile.activity.BaseActivity
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_event.*


class AddEventActivity : BaseActivity() {

    var descriptionData = arrayOf("General", "Images", "Description", "Sell tickets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.biletum.R.layout.activity_add_event)



        step_bar.setStateDescriptionData(descriptionData)
        Handler().postDelayed({     step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)}, 2000)




    }
}