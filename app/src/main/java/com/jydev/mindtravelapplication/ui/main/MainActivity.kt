package com.jydev.mindtravelapplication.ui.main

import androidx.fragment.app.Fragment
import com.jydev.mindtravelapplication.R
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMainBinding
import com.jydev.mindtravelapplication.ui.main.mindshare.MindShareFragment
import com.jydev.mindtravelapplication.ui.main.travel.TravelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val fragmentList = arrayListOf(TravelFragment(),MindShareFragment())
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityMainBinding.initView(){
        changeFragment(fragmentList[0])
        navigation.setOnItemSelectedListener{
            val fragment = when(it.itemId){
                R.id.action_travel -> fragmentList[0]
                else -> fragmentList[1]
            }
            changeFragment(fragment)
            true
        }
    }

    private fun changeFragment(fragment : Fragment){
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(binding.fragment.id,fragment)
        beginTransaction.commit()
    }
}