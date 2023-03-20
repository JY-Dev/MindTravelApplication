package com.jydev.mindtravelapplication.ui.main.mindshare

import com.google.android.material.tabs.TabLayout
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.databinding.FragmentMindShareBinding

class MindShareFragment : BaseFragment<FragmentMindShareBinding>(FragmentMindShareBinding::inflate) {
    private val tabList = arrayOf("고민상담","일상")
    override fun onViewCreateLifeCycle() {
        binding.initView()
    }

    private fun FragmentMindShareBinding.initView(){
        tabList.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
    }
}