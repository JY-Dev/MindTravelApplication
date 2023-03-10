package com.jydev.mindtravelapplication.ui

import android.content.Intent
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityStartBinding
import com.jydev.mindtravelapplication.ui.login.LoginActivity

class StartActivity : BaseActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityStartBinding.initView(){
        startButton.setOnClickListener {
            startActivity(Intent(this@StartActivity, LoginActivity::class.java))
            finish()
        }
    }
}