package com.example.biletum.events.events_list


import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.activity.*
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.example.biletum.events.EventsViewModel

import com.example.biletum.fragments.BaseFragment
import com.example.biletum.helper.USER_KEY

import kotlinx.android.synthetic.main.fragment_event.*
import javax.inject.Inject

class EventsFragment : BaseFragment(R.layout.fragment_event){

    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var eventAdapter: EventAdapter


    override fun onResume() {
        super.onResume()
        viewModel.getListEvents(sharedPreferences.getString(USER_KEY,"").toString())


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = getViewModel(EventsViewModel::class.java)
        viewModel.getListEventsData.observe(this, Observer {
            when (it.List.isNotEmpty()) {
                true -> {
                    handleListEventSuccess(it.List)
                }
                false -> {
                    handleNotEmptyList()
                }
            }
        })

        iv_profile.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        btn_filter.setOnClickListener {
            val intent = Intent(activity, EventsFilterActivity::class.java)
            startActivityForResult(intent, 1)
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {


                }

            }
    }

    private fun handleNotEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleListEventSuccess(data: List<EventItemResponse>) {
        rv_events_list?.apply {
            eventAdapter.collection = data
            adapter = eventAdapter
        }
    }


    

}