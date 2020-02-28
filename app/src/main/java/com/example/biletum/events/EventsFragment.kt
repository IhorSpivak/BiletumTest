package com.example.biletum.events

import android.app.ActivityOptions
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.biletum.R
import com.example.biletum.data.network.model.responses.events.EventItemResponse
import com.example.biletum.fragments.BaseFragment
import com.github.florent37.kotlin.pleaseanimate.please
import kotlinx.android.synthetic.main.fragment_event.*
import javax.inject.Inject

class EventsFragment : BaseFragment(R.layout.fragment_event){

    @Inject
    lateinit var eventAdapter:EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rv_events_list?.apply {
//            eventAdapter.collection = List<EventItemResponse>
//            adapter = EventAdapter
//            walletListAdapter.onItemClickQR = { item -> onSharedWallet(item.address)
            }


        }


}