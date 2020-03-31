package com.example.biletum.view.profile.events.events_list

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import com.example.biletum.R
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.events.event_add.activity.AddEventActivity
import com.example.biletum.view.profile.events.filter.EventsFilterActivity
import com.example.biletum.view.profile.profile.ProfileActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {

                    return@OnCompleteListener
                }

                val token = task.result?.token


            })

        setupViewPager()

        iv_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

        btn_filter.setOnClickListener {
            val intent = Intent(this, EventsFilterActivity::class.java)
            startActivityForResult(intent, 1)
        }

        btn_add_event.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }


    }

    private fun setupViewPager() {

        val adapter = MyFragmentPagerAdapter(getSupportFragmentManager())


        adapter.addFragment(MyEventsFragment.newInstance(),"My events")
        adapter.addFragment(EventsFragment.newInstance(),"All events")






        viewpager!!.adapter = adapter

        tabs!!.setupWithViewPager(viewpager)
    }
}
