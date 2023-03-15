package com.jydev.mindtravelapplication.ui.start

import android.content.Intent
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.ui.main.MainActivity
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityStartBinding
import com.jydev.mindtravelapplication.ui.editnickname.EditNicknameActivity
import com.jydev.mindtravelapplication.ui.login.LoginActivity
import com.jydev.mindtravelapplication.ui.start.StartViewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {
    private val startViewModel by viewModels<StartViewModel>()
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    private fun observeData(){
        startViewModel.moveScreen.observe(this){
            val intent : Intent = when(it){
                is MoveScreen.MainScreen -> {
                    Intent(this@StartActivity, MainActivity::class.java)
                }
                is MoveScreen.LoginScreen -> {
                    Intent(this@StartActivity,LoginActivity::class.java)
                }
                is MoveScreen.EditNicknameScreen -> {
                    Intent(this@StartActivity,EditNicknameActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }
    }

    private fun ActivityStartBinding.initView(){
        startButton.setOnClickListener {
            startViewModel.moveScreen()
        }
    }
}