package com.example.biletum.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.biletum.data.network.model.responses.events.CountryItem
import com.example.biletum.login.CountryAdapter
import kotlinx.android.synthetic.main.activity_country_list.*

import javax.inject.Inject


import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


class CountryListActivity : BaseActivity() {



    val countryes = mutableListOf(CountryItem("Ukraine","+38","UA",1), CountryItem("Russian Federation","+7","RU",2),
        CountryItem("Azerbaijan","+994","AZ",3), CountryItem("Wonder LAnd","+38","WL",4))
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(com.example.biletum.R.layout.activity_country_list)


        recycler_view?.apply {
            countryAdapter.collection = countryes
            adapter = countryAdapter
            countryAdapter.onItemClick = { item -> onClickCountry(item) }
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
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(recycler_view.windowToken, 0)
        val intent = Intent()
        intent.putExtra("name", item.shortName)
        intent.putExtra("code", item.code)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }


}