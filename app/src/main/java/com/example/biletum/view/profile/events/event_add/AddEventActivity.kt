package com.example.biletum.view.profile.events.event_add

import android.os.Bundle
import android.os.Handler
import androidx.viewpager.widget.ViewPager
import com.example.biletum.R
import com.example.biletum.view.profile.activity.BaseActivity
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_event.*


class AddEventActivity : BaseActivity() {

    var descriptionData = arrayOf("General", "Images", "Description", "Sell tickets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.biletum.R.layout.activity_add_event)



        step_bar.setStateDescriptionData(descriptionData)
        step_bar.setStateDescriptionTypeface("res/font/sf_ligth.otf");
        step_bar.setStateNumberTypeface("fonts/Questrial-Regular.ttf");

        setupViewPager()

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        step_bar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
                        tv_title_steps.text = "General Information"
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

    }

    private fun setupViewPager() {

        val adapter = MyFragmentPagerAdapter(getSupportFragmentManager())

        adapter.addFragment(AddEventFragmentStep1.newInstance())
        adapter.addFragment(AddEventFragmentStep2.newInstance())
        adapter.addFragment(AddEventFragmentStep3.newInstance())
        adapter.addFragment(AddEventFragmentStep4.newInstance())

        view_pager!!.adapter = adapter


    }



}