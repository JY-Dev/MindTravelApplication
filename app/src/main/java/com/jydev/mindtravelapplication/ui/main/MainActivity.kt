package com.jydev.mindtravelapplication.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.jydev.mindtravelapplication.R
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMainBinding
import com.jydev.mindtravelapplication.ui.main.mindshare.MindShareFragment
import com.jydev.mindtravelapplication.ui.main.travel.TravelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val pagerAdapter by lazy {
        MainViewPagerAdapter(this)
    }
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityMainBinding.initView(){
        viewPager.adapter = pagerAdapter
        navigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.action_travel -> viewPager.currentItem = 0
                R.id.action_consulting -> viewPager.currentItem = 1
                else -> viewPager.currentItem = 2
            }
            true
        }
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    navigation.menu.getItem(position).isChecked = true
                }
            }
        )
    }

}