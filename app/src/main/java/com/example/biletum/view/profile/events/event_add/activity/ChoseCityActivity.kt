package com.example.biletum.view.profile.events.event_add.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.biletum.data.network.model.models.CityItem
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.events.event_add.adapters.CityAdapter
import com.example.biletum.view_models.LocationViewModel
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ChoseCityActivity : BaseActivity() {
    private lateinit var locationViewModel: LocationViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var cityAdapter: CityAdapter

    var cityes : List<CityItem> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.biletum.R.layout.activity_list)

        locationViewModel = getViewModel(LocationViewModel::class.java)

        locationViewModel.cityListData.observe(this, androidx.lifecycle.Observer {
            when (it.List.isNotEmpty()) {
                true -> {
                    handleListCityesSuccess(it.List)
                }
                false -> {
                    handleEmptyList()
                }
            }
        })

        val intent : Intent = intent
        locationViewModel.getCityList(sharedPreferences.getString(USER_KEY,"").toString(), intent.getIntExtra("id",0))



        btn_back.setOnClickListener {
            onBackPressed()

        }

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun handleListCityesSuccess(list: List<CityItem>) {
        cityes = list
        recycler_view?.apply {
            cityAdapter.collection = list
            adapter = cityAdapter
            cityAdapter.onItemClick = { item -> onClickCity(item) }
        }
    }

    private fun handleEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun onClickCity(item: CityItem) {
        val intent = Intent()
        intent.putExtra("name", item.name)
        intent.putExtra("id", item.id)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }

    fun filter(text: String) {
        val temp = ArrayList<CityItem>()
        for (d in cityes) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.toLowerCase().contains(text)) {
                temp.add(d)
            }
        }

        cityAdapter.updateList(temp)
    }

}