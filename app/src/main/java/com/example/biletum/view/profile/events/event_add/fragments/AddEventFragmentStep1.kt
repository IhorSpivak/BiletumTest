package com.example.biletum.view.profile.events.event_add.fragments


import android.R
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
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.biletum.data.network.model.dto.CategoryDataItem
import com.example.biletum.data.network.model.models.EventCategory
import com.example.biletum.data.network.model.models.EventType
import com.example.biletum.data.network.model.models.EventTypeEvent

import com.example.biletum.helper.DateHelper
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.events.event_add.activity.ChoseCategoryEventActivity
import com.example.biletum.view.profile.events.event_add.activity.ChoseCityActivity
import com.example.biletum.view.profile.events.event_add.activity.ChoseCountryActivity
import com.example.biletum.view.profile.events.event_add.activity.ChoseTypeEventActivity
import com.example.biletum.view_models.EventShareViewModel
import com.example.biletum.view_models.LocationViewModel
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.fragment_add_event1.*
import kotlinx.android.synthetic.main.fragment_add_event1.name_text_input
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*
import androidx.viewpager.widget.ViewPager
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class AddEventFragmentStep1: BaseFragment(com.example.biletum.R.layout.fragment_add_event1) {

    private lateinit var viewModel: EventsViewModel
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var eventShareViewModel: EventShareViewModel
    private val GALLERY = 5
    private val CAMERA = 6
    private var ID_CITY = 0
    private var ID_COUNTY = 0
    private var FLAG = false
    var listTypes = listOf<EventType>()
    var listCategoryes = listOf<EventCategory>()


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var calendar : Calendar? = null
    private var dateEvent : String? = ""
    private var timeEvent : String? = ""
    private val IMAGE_DIRECTORY = "/demonuts"




    companion object {
        fun newInstance(): AddEventFragmentStep1 {

            val f =
                AddEventFragmentStep1()

            val bdl = Bundle(1)

            f.setArguments(bdl)

            return f

        }
    }

    override fun onResume() {
        super.onResume()
        ed_city.clearFocus()
        ed_country.clearFocus()
        ed_type.clearFocus()
        ed_event_category.clearFocus()
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
            when (it.List.isNotEmpty()) {
                true -> {
                    handleListCategotyesSuccess(it.List)
                }
                false -> {
                    handleEmptyList()
                }
            }
        })

        ed_name.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    name_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    name_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_start_day.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    onStartDateClick()
                    name_start_date_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else {
                    name_start_date_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }
        ed_end_date.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    onEndDateClick()
                    end_date_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else{
                    end_date_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_country.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    chose_country_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                    val intent = Intent(activity, ChoseCountryActivity::class.java)
                    startActivityForResult(intent, 1)
                } else {
                    chose_country_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
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
                    chose_city_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_field_create_event_focus)
                } else{
                    chose_city_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.bg_input)
                }
            }
        }

        ed_event_category.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    val intent = Intent(activity, ChoseCategoryEventActivity::class.java)
                    val categoryData = listCategoryes?.let { CategoryDataItem(it) }
                    intent.putExtra("data", categoryData)
                    startActivityForResult(intent, 3)
                }
            }
        }

        ed_city.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    if(ID_COUNTY == 0){
                        ed_city.clearFocus()
                        tv_alert_location.visibility = View.VISIBLE
                        tv_alert_location.text = "This field is required"
                        chose_country_input.error = "This field is required"
                        chose_country_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
                    } else {
                        val intent = Intent(activity, ChoseCityActivity::class.java)
                        intent.putExtra("id", ID_COUNTY)
                        startActivityForResult(intent, 4)
                    }

                }
            }
        }

        ed_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    name_text_input.setBackgroundResource(com.example.biletum.R.drawable.bg_field_create_event_focus)
                    tv_alert_name.visibility = View.INVISIBLE
                    name_text_input.isErrorEnabled = false

                }
            }
        })

        ed_type.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    name_type_input.setBackgroundResource(com.example.biletum.R.drawable.bg_field_create_event_focus)
                    tv_alert_type.visibility = View.INVISIBLE
                    name_type_input.isErrorEnabled = false

                }
            }
        })

        ed_event_category.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    name_category_input.setBackgroundResource(com.example.biletum.R.drawable.bg_field_create_event_focus)
                    tv_alert_category.visibility = View.INVISIBLE
                    name_category_input.isErrorEnabled = false
                }
            }
        })

        ed_country.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    chose_country_input.setBackgroundResource(com.example.biletum.R.drawable.bg_field_create_event_focus)
                    tv_alert_location.visibility = View.INVISIBLE
                    chose_country_input.isErrorEnabled = false
                }
            }
        })

        val view = activity!!.view_pager
        view.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(!FLAG){

                    view.setCurrentItem(0, true)
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }


        })

        btn_go_to_step_2.setOnClickListener {
            submitForm()
        }

    }

    private fun submitForm() {

        if (!validateFieldName()) {
            return
        }
        if (!validateFieldType()) {
            return
        }
        if (!validateFieldCategory()) {
            return
        }
        if (!validateFieldCountry()) {
            return
        }
        goToSecondStep()
    }

    private fun goToSecondStep() {
        FLAG = true
        addParametersToEvent()
        activity!!.view_pager.setCurrentItem(1, true)
    }

    private fun validateFieldCountry(): Boolean {
        if (ed_country.text.toString().isEmpty()) {
            tv_alert_location.visibility = View.VISIBLE
            tv_alert_location.text = "This field is required"
            chose_country_input.error = "This field is required"
            chose_country_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
            nestedScrollView.scrollTo(0,0)
            return false
        } else {
            chose_country_input.isErrorEnabled = false
        }
        return true
    }

    private fun validateFieldCategory(): Boolean {
        if (ed_event_category.text.toString().isEmpty()) {
            tv_alert_category.visibility = View.VISIBLE
            tv_alert_category.text = "Choose your event category"
            name_category_input.error = "Choose your event category"
            name_category_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
            nestedScrollView.scrollTo(0,0)
            return false
        } else {
            name_category_input.isErrorEnabled = false
        }
        return true
    }

    private fun validateFieldType(): Boolean {
        if (ed_type.text.toString().isEmpty()) {
            tv_alert_type.visibility = View.VISIBLE
            tv_alert_type.text = "Choose your event type"
            name_type_input.error = "Choose your event type"
            name_type_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
            nestedScrollView.scrollTo(0,0)
            return false
        } else {
            name_text_input.isErrorEnabled = false
        }
        return true
    }

    private fun validateFieldName(): Boolean {
        if (ed_name.text.toString().isEmpty()) {
            tv_alert_name.visibility = View.VISIBLE
            tv_alert_name.text = "Type your event name"
            name_text_input.error = "Type your event name"
            name_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
            nestedScrollView.scrollTo(0,0)
            return false
        } else {
            name_text_input.isErrorEnabled = false
        }
        return true

    }

    private fun handleListCategotyesSuccess(list: List<EventCategory>) {
        listCategoryes = list
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
                    ID_COUNTY = data!!.getIntExtra("id",0)
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
        listCategoryes = dataList.list
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
            Html.fromHtml("<font color=\"#3AA1FF\">Изминение сохранено</font>"),
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
        currentCalendar.time = Date()
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
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    fun onStartDateClick() {
        val currentCalendar = Calendar.getInstance()
        currentCalendar.time = Date()
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
