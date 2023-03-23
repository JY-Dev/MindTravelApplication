package com.jydev.mindtravelapplication.ui.main.mindshare.post

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory

class MindSharePostPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    private val mindSharePostCategory = MindSharePostCategory.values()
    override fun getItemCount(): Int {
        return mindSharePostCategory.size
    }

    override fun createFragment(position: Int): Fragment {
        return MindSharePostFragment.getMindSharePostFragment(mindSharePostCategory[position])
    }
}