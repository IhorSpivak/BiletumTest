package com.example.biletum.view.profile.events.event_add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import com.example.biletum.data.network.model.models.TypeItem
import com.example.biletum.view.profile.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ChoseTypeEventActivity : BaseActivity() {


    val countryes = mutableListOf(
        TypeItem("Concert", 1),
        TypeItem("Congress", 2),
        TypeItem("Seminar", 3),
        TypeItem("Show", 4)

    )
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var typesEventAdapter: TypesEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(com.example.biletum.R.layout.activity_list)


        recycler_view?.apply {
            typesEventAdapter.collection = countryes
            adapter = typesEventAdapter
            typesEventAdapter.onItemClick = { item -> onTypeClick(item) }
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
        val temp = ArrayList<TypeItem>()
        for (d in countryes) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.toLowerCase().contains(text)) {
                temp.add(d)
            }
        }

        typesEventAdapter.updateList(temp)
    }

    private fun onTypeClick(item: TypeItem) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(recycler_view.windowToken, 0)
        val intent = Intent()
        intent.putExtra("name", item.name)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }
}
