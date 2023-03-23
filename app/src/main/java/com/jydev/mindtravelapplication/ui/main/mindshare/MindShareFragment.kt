package com.jydev.mindtravelapplication.ui.main.mindshare

import android.content.Intent
import com.google.android.material.tabs.TabLayoutMediator
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.databinding.FragmentMindShareBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory
import com.jydev.mindtravelapplication.ui.main.mindshare.post.MindSharePostPagerAdapter
import com.jydev.mindtravelapplication.ui.main.mindshare.post.write.MindSharePostWriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindShareFragment :
    BaseFragment<FragmentMindShareBinding>(FragmentMindShareBinding::inflate) {
    private val tabList = MindSharePostCategory.values().map {
        it.text
    }
    private val mindSharePostPagerAdapter by lazy {
        MindSharePostPagerAdapter(requireActivity())
    }

    override fun onViewCreateLifeCycle() {
        binding.initView()
    }

    private fun FragmentMindShareBinding.initView() {
        viewPager.adapter = mindSharePostPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
        writePostButton.setOnClickListener {
            context?.let {
                startActivity(Intent(context, MindSharePostWriteActivity::class.java))
            }
        }
    }
}