package com.jydev.mindtravelapplication.ui.main

import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityPostWriteBinding

class PostWriteActivity : BaseActivity<ActivityPostWriteBinding>(ActivityPostWriteBinding::inflate) {
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityPostWriteBinding.initView(){

    }
}