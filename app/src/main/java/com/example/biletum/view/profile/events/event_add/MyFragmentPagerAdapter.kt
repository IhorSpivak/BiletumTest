package com.example.biletum.view.profile.events.event_add

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


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
            0 // Fragment # 0 - This will show FirstFragment
            -> return AddEventFragmentStep1.newInstance()
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return AddEventFragmentStep2.newInstance()
            2 // Fragment # 1 - This will show SecondFragment
            -> return AddEventFragmentStep3.newInstance()
            else -> return AddEventFragmentStep1.newInstance()
        }
    }
}