package com.jydev.mindtravelapplication.ui.editnickname

import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityEditNicknameBinding

class EditNicknameActivity : BaseActivity<ActivityEditNicknameBinding>(ActivityEditNicknameBinding::inflate) {
    override fun onCreateLifeCycle() {
        binding.initView()
    }

    private fun ActivityEditNicknameBinding.initView(){
        settingButton.setOnClickListener {

        }
    }
}