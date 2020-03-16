package com.example.biletum.view.profile.events.events_list


import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.biletum.R
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.example.biletum.view_models.EventsViewModel

import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.events.filter.EventsFilterActivity
import com.example.biletum.view.profile.profile.ProfileActivity

import kotlinx.android.synthetic.main.fragment_event.*
import javax.inject.Inject

class EventsFragment : BaseFragment(R.layout.fragment_event), SwipeRefreshLayout.OnRefreshListener{


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

        progressBarShow(true)
        viewModel = getViewModel(EventsViewModel::class.java)
        viewModel.getListEventsData.observe(this, Observer {
            when (it.List.isNotEmpty()) {
                true -> {
                    handleListEventSuccess(it.List)
                }
                false -> {
                    handleEmptyList()
                }
            }
        })



        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getListEvents(sharedPreferences.getString(USER_KEY,"").toString())
            rv_events_list.visibility = View.GONE


        }

        iv_profile.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        }

        btn_filter.setOnClickListener {
            val intent = Intent(activity, EventsFilterActivity::class.java)
            startActivityForResult(intent, 1)
        }

    }

    override fun onRefresh() {
        if (swipe_refresh_layout.isRefreshing) {
            swipe_refresh_layout.isRefreshing = false
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {

                }

            }
    }

    private fun handleEmptyList() {

    }

    private fun handleListEventSuccess(data: List<EventItemResponse>) {
        rv_events_list?.apply {
            eventAdapter.collection = data
            adapter = eventAdapter
            progressBarShow(false)
            rv_events_list.visibility = View.VISIBLE
            Handler().postDelayed({ swipe_refresh_layout.isRefreshing = false }, 500)

        }
    }


    private fun progressBarShow(showProgress: Boolean) {
      when(showProgress){
          true -> {
            pb.visibility = View.VISIBLE
          }
          false -> {
              pb.visibility = View.GONE

          }
      }
    }

    

}