package com.example.biletum.view.profile.events.event_add.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.biletum.data.network.model.dto.CategoryDataItem
import com.example.biletum.data.network.model.models.EventCategory
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.events.event_add.adapters.CategoryEventAdapter
import kotlinx.android.synthetic.main.activity_category_list.*
import javax.inject.Inject

class ChoseCategoryEventActivity : BaseActivity() {

    var dataList : CategoryDataItem? = null

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var typesEventAdapter: CategoryEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(com.example.biletum.R.layout.activity_category_list)

        dataList = intent.getParcelableExtra<CategoryDataItem>("data")

        recycler_view?.apply {
            typesEventAdapter.collection = dataList!!.list
            adapter = typesEventAdapter
        }
        btn_back.setOnClickListener {
            onBackPressed()

        }

        btn_save.setOnClickListener {
           onSaveCategotyesClick()

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
        val temp = ArrayList<EventCategory>()
        for (d in dataList!!.list) {
            if (d.name.toLowerCase().contains(text)) {
                temp.add(d)
            }
        }

        typesEventAdapter.updateList(temp)
    }

    private fun onSaveCategotyesClick() {
        val list : List<EventCategory> = dataList!!.list
        val categoryData = CategoryDataItem(list)
        val intent = Intent()
        intent.putExtra("data", categoryData)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }
}
