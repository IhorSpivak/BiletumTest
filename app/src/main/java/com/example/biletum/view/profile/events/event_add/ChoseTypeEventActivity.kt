package com.example.biletum.view.profile.events.event_add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import com.example.biletum.data.network.model.models.EventType
import com.example.biletum.data.network.model.models.EventTypeEvent
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view_models.EventsViewModel
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject
import androidx.recyclerview.widget.GridLayoutManager
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.activity_list.btn_back
import kotlinx.android.synthetic.main.activity_list.ed_search
import kotlinx.android.synthetic.main.activity_list.recycler_view


class ChoseTypeEventActivity : BaseActivity() {


    private lateinit var viewModel: EventsViewModel

    var listTypes = emptyList<EventType>()

    var dataList : EventTypeEvent? = null
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var typesEventAdapter: TypesEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(com.example.biletum.R.layout.activity_list)

        dataList = intent.getParcelableExtra<EventTypeEvent>("data")

        viewModel = getViewModel(EventsViewModel::class.java)
        viewModel.getTypesEvent(sharedPreferences.getString(USER_KEY,"").toString())


        recycler_view?.apply {
            recycler_view.layoutManager = GridLayoutManager(context, 2)
            typesEventAdapter.collection = dataList!!.list
            adapter = typesEventAdapter
        }

        btn_back.setOnClickListener {
            onBackPressed()

        }

        btn_go_to_step_2.setOnClickListener {
            onSaveTypes()
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

    private fun onSaveTypes() {
        val list : List<EventType> = dataList!!.list
        val typesData = EventTypeEvent(list)
        val intent = Intent()
        intent.putExtra("data", typesData)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }

    private fun handleEmptyList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun filter(text: String) {
        val temp = ArrayList<EventType>()
        for (d in listTypes) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.name.toLowerCase().contains(text)) {
                temp.add(d)
            }
        }

        typesEventAdapter.updateList(temp)
    }

    private fun onTypeClick(item: EventType) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(recycler_view.windowToken, 0)
        val intent = Intent()
        intent.putExtra("name", item.name)
        intent.putExtra("id", item.id)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }
}
