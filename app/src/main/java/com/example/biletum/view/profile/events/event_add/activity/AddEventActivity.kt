package com.example.biletum.view.profile.events.event_add.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager

import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep1
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep2
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep3
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep4
import com.example.biletum.view.profile.events.event_add.adapters.MyFragmentPagerAdapter
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_event.*





class AddEventActivity : BaseActivity() {

    companion object CreateEvent {
        var title  :String = ""
        var descr :String = ""
        val image :String= ""
        var date_start :String= ""
        var date_end :String= ""
        val region :String= ""
        val url  :String= ""
        val photos :String= ""
        var location :String= ""
        val agenda :String= ""
        val contact :String= ""
        val access_type :String= ""

    }


    var descriptionData = arrayOf("General", "Images", "Description", "Sell tickets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.biletum.R.layout.activity_add_event)

        setupViewPager()
        step_bar.setStateDescriptionData(descriptionData)
        step_bar.setStateDescriptionTypeface("font/sf_medium")
        step_bar.setStateNumberTypeface("font/sf_medium")
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        tv_title_steps.text = "General Information"
                        step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
                    }
                    1 -> {
                        step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
                        tv_title_steps.text = "Images"
                    }
                    2 -> {
                        step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
                        tv_title_steps.text = "Description"
                    }
                    3 -> {
                        step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
                        tv_title_steps.text = "Sell ticket"
                    }

                }
            }
        })

        btn_back.setOnClickListener {
           onBackPressed()
        }

    }

    private fun setupViewPager() {

        val adapter =
            MyFragmentPagerAdapter(
                getSupportFragmentManager()
            )

        adapter.addFragment(AddEventFragmentStep1.newInstance())
        adapter.addFragment(AddEventFragmentStep2.newInstance())
        adapter.addFragment(AddEventFragmentStep3.newInstance())
        adapter.addFragment(AddEventFragmentStep4.newInstance())

        view_pager!!.adapter = adapter


    }

    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            view_pager.currentItem = view_pager.currentItem - 1
        }
    }



}