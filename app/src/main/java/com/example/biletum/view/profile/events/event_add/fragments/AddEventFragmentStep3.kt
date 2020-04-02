package com.example.biletum.view.profile.events.event_add.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.biletum.R
import com.example.biletum.view.profile.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_event3.*

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


        ed_description.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    description_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    description_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_agenda.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    agenda_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    agenda_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_website.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    website_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    website_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_contacts.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    contacts_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    contacts_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }
    }
}