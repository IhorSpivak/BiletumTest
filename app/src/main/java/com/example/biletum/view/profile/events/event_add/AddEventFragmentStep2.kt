package com.example.biletum.view.profile.events.event_add

import android.os.Bundle
import android.view.View
import com.example.biletum.R
import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.view_models.EventsViewModel
import kotlinx.android.synthetic.main.fragment_add_event1.*


class AddEventFragmentStep2: BaseFragment(R.layout.fragment_add_event2) {

    private lateinit var viewModel: EventsViewModel

    companion object {
        fun newInstance(): AddEventFragmentStep2 {

            val f = AddEventFragmentStep2()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addParametersToEvent()


    }

    private fun addParametersToEvent() {
        AddEventActivity.CreateEvent.descr = "test"

    }
}