package com.example.biletum.view.profile.events.event_add.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.biletum.R
import com.example.biletum.data.network.model.requests.events.AddEventRequest
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.view_models.EventShareViewModel
import com.example.biletum.view_models.EventsViewModel
import kotlinx.android.synthetic.main.fragment_add_event4.*
import javax.inject.Inject

class AddEventFragmentStep4: BaseFragment(R.layout.fragment_add_event4) {


    var title: String? = ""
    var desciption: String? = ""
    var start_date: String? = ""
    var end_date: String? = ""
    var location: String? = ""
    var contact: String? = ""
    var categories: List<Any>? = null
    var photos: List<String>? = null
    var agenda:  List<String>? = null
    var type: Int? = null
    private lateinit var viewModel: EventsViewModel
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        fun newInstance(): AddEventFragmentStep4 {

            val f =
                AddEventFragmentStep4()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = getViewModel(EventsViewModel::class.java)
        val model=ViewModelProviders.of(activity!!).get(EventShareViewModel::class.java)
        viewModel.addEventData.observe(this, Observer {

        })

        model.title.observe(this, Observer {
            title = it.toString()
        })

        model.desription.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
              desciption = o!!.toString()
            }
        })

        model.startDate.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                start_date = o.toString()
            }
        })

        model.endDate.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                end_date = o.toString()
            }
        })

        model.type.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                type = o.toString().toIntOrNull()
            }
        })

        model.category.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {

            }
        })

        model.photos.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {

            }
        })

        model.agenda.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {

            }
        })


        btn_add_event.setOnClickListener {
                viewModel.addEvent(sharedPreferences.getString(USER_KEY,"").toString(), AddEventRequest(title.toString(),desciption.toString(),
                    emptyList(), emptyList(),
                    "2020-03-01T15:45","2020-03-02T17:45","test",
                    listOf(1, 2),location.toString(),1,1,contact.toString(),1))
            }






        if (!ed_tickets_url.text.toString().isValidUrl()) {
            //some code
        }else{
            //some code
        }

    }

    fun String.isValidUrl(): Boolean = Patterns.WEB_URL.matcher(this).matches()

    private fun addParametersToEvent() {

    }
}