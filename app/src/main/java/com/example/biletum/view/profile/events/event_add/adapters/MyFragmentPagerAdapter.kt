package com.example.biletum.view.profile.events.event_add.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep1
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep2
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep3
import com.example.biletum.view.profile.events.event_add.fragments.AddEventFragmentStep4


class MyFragmentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList: ArrayList<Fragment> = ArrayList()
    private val mFragmentTitleList: ArrayList<String> = ArrayList()


    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return AddEventFragmentStep1.newInstance()
            1 -> return AddEventFragmentStep2.newInstance()
            2 -> return AddEventFragmentStep3.newInstance()
            3 -> return AddEventFragmentStep4.newInstance()
            else -> return AddEventFragmentStep1.newInstance()
        }
    }
}