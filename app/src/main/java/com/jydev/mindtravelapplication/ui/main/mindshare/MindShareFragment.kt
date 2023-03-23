package com.jydev.mindtravelapplication.ui.main.mindshare

import android.content.Intent
import com.google.android.material.tabs.TabLayout
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.databinding.FragmentMindShareBinding
import com.jydev.mindtravelapplication.ui.main.mindshare.post.write.MindSharePostWriteActivity

class MindShareFragment : BaseFragment<FragmentMindShareBinding>(FragmentMindShareBinding::inflate) {
    private val tabList = arrayOf("고민상담","일상")
    override fun onViewCreateLifeCycle() {
        binding.initView()
    }

    private fun FragmentMindShareBinding.initView(){
        tabList.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
        writePostButton.setOnClickListener {
            context?.let {
                startActivity(Intent(context,MindSharePostWriteActivity::class.java))
            }
        }
    }
}