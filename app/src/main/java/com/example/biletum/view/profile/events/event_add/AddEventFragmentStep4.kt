package com.example.biletum.view.profile.events.event_add

import android.os.Bundle
import android.view.View
import com.example.biletum.R
import com.example.biletum.view.profile.BaseFragment

class AddEventFragmentStep4: BaseFragment(R.layout.fragment_add_event4) {

    companion object {
        fun newInstance(): AddEventFragmentStep4 {

            val f = AddEventFragmentStep4()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
}