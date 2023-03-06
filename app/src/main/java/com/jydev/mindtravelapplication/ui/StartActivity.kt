package com.jydev.mindtravelapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.jydev.mindtravelapplication.R
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityStartBinding

class StartActivity : BaseActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityStartBinding.initView(){
        startButton.setOnClickListener {
            startActivity(Intent(this@StartActivity,LoginActivity::class.java))
            finish()
        }
    }
}