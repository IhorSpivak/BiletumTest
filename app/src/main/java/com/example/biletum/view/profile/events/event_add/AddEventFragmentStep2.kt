package com.example.biletum.view.profile.events.event_add

import android.os.Bundle
import android.view.View
import com.example.biletum.R
import com.example.biletum.view.profile.BaseFragment


class AddEventFragmentStep2: BaseFragment(R.layout.fragment_add_event2) {



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


    }
}