package com.example.biletum.view.profile.events.event_add


import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.biletum.view_models.EventsViewModel
import com.example.biletum.view.profile.BaseFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Handler
import android.view.Gravity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProviders
import com.example.biletum.data.network.model.dto.CategoryDataItem
import com.example.biletum.data.network.model.models.CategoryItem
import com.example.biletum.data.network.model.models.EventType
import com.example.biletum.data.network.model.models.EventTypeEvent

import com.example.biletum.helper.DateHelper
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view_models.EventShareViewModel
import com.example.biletum.view_models.LocationViewModel
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.fragment_add_event1.*
import java.util.*


class AddEventFragmentStep1: BaseFragment(com.example.biletum.R.layout.fragment_add_event1) {

    private lateinit var viewModel: EventsViewModel
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var eventShareViewModel: EventShareViewModel
    private val GALLERY = 5
    private val CAMERA = 6
    var listTypes = listOf<EventType>()
    var list = listOf<CategoryItem>()


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var calendar : Calendar? = null
    private var dateEvent : String? = ""
    private var timeEvent : String? = ""
    private val IMAGE_DIRECTORY = "/demonuts"




    companion object {
        fun newInstance(): AddEventFragmentStep1 {

            val f = AddEventFragmentStep1()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(EventsViewModel::class.java)

        viewModel.getTypesEvent(sharedPreferences.getString(USER_KEY,"").toString())
        viewModel.getCategoryEvent(sharedPreferences.getString(USER_KEY,"").toString())

        eventShareViewModel= ViewModelProviders.of(activity!!).get(EventShareViewModel::class.java)


        viewModel.getListTypeEvents.observe(this, androidx.lifecycle.Observer {
            when (it.List.isNotEmpty()) {
                true -> {
                    handleListTypeSuccess(it.List)
                }
                false -> {
                    handleEmptyList()
                }
            }
        })

        viewModel.getListCategoryEvents.observe(this, androidx.lifecycle.Observer {
            when (it.list.isNotEmpty()) {
                true -> {
                    handleListCategotyesSuccess(it.list)
                }
                false -> {
                    handleEmptyList()
                }
            }
        })

        ed_start_day.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    onStartDateClick()
                }
            }
        }
        ed_end_date.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    onEndDateClick()
                }
            }
        }

        ed_country.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    val intent = Intent(activity, ChoseLocationActivity::class.java)
                    startActivityForResult(intent, 1)
                }
            }
        }

        ed_type.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    val intent = Intent(activity, ChoseTypeEventActivity::class.java)
                    val typesData = listTypes?.let { EventTypeEvent(it) }
                    intent.putExtra("data", typesData)
                    startActivityForResult(intent, 2)
                }
            }
        }

        ed_event_category.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    val intent = Intent(activity, ChoseCategoryEventActivity::class.java)
                    val categoryData = list?.let { CategoryDataItem(it) }
                    intent.putExtra("data", categoryData)
                    startActivityForResult(intent, 3)
                }
            }
        }

        ed_city.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    val intent = Intent(activity, ChoseLocationActivity::class.java)
                    intent.putExtra("Country", "Ukraine")
                    startActivityForResult(intent, 4)
                }
            }
        }

        btn_go_to_step_2.setOnClickListener {
            addParametersToEvent()
            activity!!.view_pager.setCurrentItem(1, true)
        }

    }

    private fun handleListCategotyesSuccess(list: Any) {

    }

    private fun handleEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleListTypeSuccess(list: List<EventType>) {
        listTypes = list
    }

    private fun addParametersToEvent() {
        eventShareViewModel!!.setTitle(ed_name.text.toString())
        eventShareViewModel!!.setStartDay(ed_start_day.text.toString())
        eventShareViewModel!!.setEndDay(ed_end_date.text.toString())
        eventShareViewModel!!.setLocation(ed_country.text.toString())
        eventShareViewModel!!.setType(ed_type.text.toString())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                if (data != null) {
                    val name = data!!.getStringExtra("name")
                    ed_country.setText(name)
                    ed_country.clearFocus()
                    Handler().postDelayed({ showSnackBar(data) }, 500)
                }
            }
            2 -> {
                if (data != null) {
                    setTypes(data)
                }
            }
            3 -> {
                if (data != null) {
                    setCategoties(data)

                }
            }
            4 -> {
                if (data != null) {
                    val name = data!!.getStringExtra("name")
                    ed_city.setText(name)
                    ed_city.clearFocus()

                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setCategoties(data: Intent) {
        val dataList = data.getParcelableExtra<CategoryDataItem>("data")
        var text: String = ""
        list = dataList.list
        dataList.list.forEach {
            if(it.isCheked)
                if(text.isEmpty()){
                    text = text + it.name
                } else {
                    text = text + "," + it.name
                }

        }
        ed_event_category.setText(text)
        ed_event_category.clearFocus()
    }

    private fun setTypes(data: Intent) {
        val dataList = data.getParcelableExtra<EventTypeEvent>("data")
        var text: String = ""
        listTypes = dataList.list
        dataList.list.forEach {
            if(it.isCheked)
                if(text.isEmpty()){
                    text = text + it.name
                } else {
                    text = text + "," + it.name
                }

        }
        ed_type.setText(text)
        ed_type.clearFocus()
    }

    private fun showSnackBar(data: Intent?) {
        Snackbar.make(
            ed_end_date,
            Html.fromHtml("<font color=\"#78E5B4\">Изминение сохранено</font>"),
            Snackbar.LENGTH_LONG
        ).apply {
            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(80, 0, 80, 80)
            params.gravity = Gravity.BOTTOM
            params.anchorGravity = Gravity.BOTTOM

            view.layoutParams = params
            view.background = resources.getDrawable(com.example.biletum.R.drawable.snackbar_background, null)
            show()
        }
    }


    fun onEndDateClick() {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.setTime(Date())
        val datePickerDialog = DatePickerDialog(
            context!!, -1, { view1, year, month, dayOfMonth ->
                calendar = Calendar.getInstance()
                calendar!!.set(Calendar.YEAR, year)
                calendar!!.set(Calendar.MONTH, month)
                calendar!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val timePickerDialog = TimePickerDialog(
                    context,
                    { view11, hourOfDay, minute ->
                        calendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar!!.set(Calendar.MINUTE, minute)
                        ed_end_date.setText(DateHelper.getStringFromCalendarForOrder(calendar!!))
                        ed_end_date.clearFocus()

                        dateEvent = DateHelper.getDateForServerFromCalendar(calendar!!)
                        timeEvent = DateHelper.getTimeForServerFromCalendar(calendar!!)
                    },
                    currentCalendar.get(Calendar.HOUR_OF_DAY),
                    currentCalendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun onStartDateClick() {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.setTime(Date())
        val datePickerDialog = DatePickerDialog(
            context!!, -1, { view1, year, month, dayOfMonth ->
                calendar = Calendar.getInstance()
                calendar!!.set(Calendar.YEAR, year)
                calendar!!.set(Calendar.MONTH, month)
                calendar!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val timePickerDialog = TimePickerDialog(
                    context,
                    { view11, hourOfDay, minute ->
                        calendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar!!.set(Calendar.MINUTE, minute)
                        ed_start_day.setText(DateHelper.getStringFromCalendarForOrder(calendar!!))
                        ed_start_day.clearFocus()
                        dateEvent = DateHelper.getDateForServerFromCalendar(calendar!!)
                        timeEvent = DateHelper.getTimeForServerFromCalendar(calendar!!)
                    },
                    currentCalendar.get(Calendar.HOUR_OF_DAY),
                    currentCalendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            },
            currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH),
            currentCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()


    }







}
