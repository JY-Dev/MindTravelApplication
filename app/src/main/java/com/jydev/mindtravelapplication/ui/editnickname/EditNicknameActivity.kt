package com.jydev.mindtravelapplication.ui.editnickname

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.ui.main.MainActivity
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityEditNicknameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNicknameActivity : BaseActivity<ActivityEditNicknameBinding>(ActivityEditNicknameBinding::inflate) {
    private val viewModel by viewModels<EditNicknameViewModel>()
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    private fun observeData(){
        viewModel.observeError()
        viewModel.changeNickname.observe(this){
            it.getContentIfNotHandled()?.let {
                intent.getStringExtra(WHERE_COME)?.let {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } ?: kotlin.run {
                    finish()
                }
            }
        }
    }

    private fun ActivityEditNicknameBinding.initView(){
        settingButton.setOnClickListener {
            val nickname = nicknameEditTextView.text.toString()
            if(nickname.isEmpty())
                Toast.makeText(this@EditNicknameActivity,"닉네임을 입력 해주세요!",Toast.LENGTH_SHORT).show()
            else
                viewModel.editNickname(nickname)
        }
    }

    companion object{
        const val WHERE_COME = "WHERE_COME"
    }
}