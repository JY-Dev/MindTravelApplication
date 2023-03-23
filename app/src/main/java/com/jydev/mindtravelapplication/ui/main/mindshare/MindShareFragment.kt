package com.jydev.mindtravelapplication.ui.main.mindshare

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
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
    private val viewModel by viewModels<MindShareViewModel>()
    private lateinit var  launcher: ActivityResultLauncher<Intent>
    private val tabList = MindSharePostCategory.values().map {
        it.text
    }
    private val mindSharePostPagerAdapter by lazy {
        MindSharePostPagerAdapter(this)
    }

    override fun onViewCreateLifeCycle() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                viewModel.sendPostEvent()
            }
        }
        binding.initView()
    }

    private fun FragmentMindShareBinding.initView() {
        viewPager.adapter = mindSharePostPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
        writePostButton.setOnClickListener {
            context?.let {
                launcher.launch(Intent(context, MindSharePostWriteActivity::class.java))
            }
        }
    }
}