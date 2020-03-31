package com.example.biletum.view.profile.events.event_add.fragments

import android.os.Bundle
import android.view.View
import com.example.biletum.R
import com.example.biletum.view.profile.BaseFragment

class AddEventFragmentStep3: BaseFragment(R.layout.fragment_add_event3) {

    companion object {
        fun newInstance(): AddEventFragmentStep3 {

            val f =
                AddEventFragmentStep3()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}