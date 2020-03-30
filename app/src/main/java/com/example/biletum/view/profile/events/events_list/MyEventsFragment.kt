package com.example.biletum.view.profile.events.events_list


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.biletum.R
import com.example.biletum.data.network.model.requests.events.EventsListRequest
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.example.biletum.view_models.EventsViewModel

import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.helper.USER_KEY


import kotlinx.android.synthetic.main.fragment_event.pb
import kotlinx.android.synthetic.main.fragment_event.rv_events_list
import kotlinx.android.synthetic.main.fragment_event.swipe_refresh_layout
import kotlinx.android.synthetic.main.my_fragment_event.*
import javax.inject.Inject

class MyEventsFragment : BaseFragment(R.layout.my_fragment_event), SwipeRefreshLayout.OnRefreshListener{


    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var eventAdapter: MyEventsAdapter

    @Inject
    lateinit var myPastEventAdapter: MyPastEventAdapter



    companion object {
        fun newInstance(): MyEventsFragment {

            val f = MyEventsFragment()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.getListEvents(sharedPreferences.getString(USER_KEY,"").toString(),  EventsListRequest(0,50,"","own"))


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
        progressBarShow(false)
        rv_events_list.visibility = View.GONE
    }

    private fun handleListEventSuccess(data: List<EventItemResponse>) {
        rv_events_list?.apply {
            eventAdapter.collection = data
            adapter = eventAdapter
            eventAdapter.onItemClickShare = { item -> shareEvent(item.id) }

        }

        rv_past_events_list?.apply {
            myPastEventAdapter.collection = data
            adapter = myPastEventAdapter

        }
        progressBarShow(false)
        rv_events_list.visibility = View.VISIBLE
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

    private fun shareEvent(id: Int) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "dev.biletum.com/ru/event/$id")
        startActivity(Intent.createChooser(shareIntent, "dev.biletum.com/ru/event/$id"))
    }





}