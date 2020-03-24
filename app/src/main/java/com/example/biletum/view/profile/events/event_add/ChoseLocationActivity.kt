package com.example.biletum.view.profile.events.event_add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import com.example.biletum.data.network.model.models.CityItem
import com.example.biletum.data.network.model.models.CountryItem
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.login.CountryAdapter
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ChoseLocationActivity : BaseActivity() {



    val countryes = mutableListOf(
        CountryItem("Ukraine", "+38", "UA", 1),
        CountryItem("Russian Federation", "+7", "RU", 2),
        CountryItem("Azerbaijan", "+994", "AZ", 3),
        CountryItem("Wonder LAnd", "+38", "WL", 4)
    )

    val cityes = mutableListOf(
        CityItem("Kyiv",  1,1,1),
        CityItem("Donetsk Federation",  2,2,2),
        CityItem("Lviv",  3,3,3),
        CityItem("Odessa",  4,4,4)
    )
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var countryAdapter: CountryAdapter

    @Inject
    lateinit var cityAdapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(com.example.biletum.R.layout.activity_list)



        val intent :Intent = intent
        when (intent.getStringExtra("Country") == null){
            true -> {
                    initCountryAdapter()
                }

            false -> {
                initCitydapter()
            }
        }



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

    private fun initCitydapter() {
        recycler_view?.apply {
            cityAdapter.collection = cityes
            adapter = cityAdapter
            cityAdapter.onItemClick = { item -> onClickCity(item) }
        }
    }

    private fun onClickCity(item: CityItem) {
        val intent = Intent()
        intent.putExtra("name", item.name)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }

    private fun initCountryAdapter() {
        recycler_view?.apply {
            countryAdapter.collection = countryes
            adapter = countryAdapter
            countryAdapter.onItemClick = { item -> onClickCountry(item) }
        }
    }

    fun filter(text: String) {
        val temp = ArrayList<CountryItem>()
        for (d in countryes) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.toLowerCase().contains(text)) {
                temp.add(d)
            }
        }

        countryAdapter.updateList(temp)
    }

    private fun onClickCountry(item: CountryItem) {
        val intent = Intent()
        intent.putExtra("name", item.name)
        intent.putExtra("code", item.code)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }


}