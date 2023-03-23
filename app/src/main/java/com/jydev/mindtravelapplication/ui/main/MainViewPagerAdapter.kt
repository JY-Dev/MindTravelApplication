package com.jydev.mindtravelapplication.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jydev.mindtravelapplication.ui.main.mindshare.MindShareFragment
import com.jydev.mindtravelapplication.ui.main.travel.TravelFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragmentList = arrayListOf(TravelFragment(), MindShareFragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}