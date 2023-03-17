package com.jydev.mindtravelapplication.ui.main.travel.record

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.databinding.ActivityRecordBinding
import com.jydev.mindtravelapplication.domain.model.Mood
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordActivity : BaseActivity<ActivityRecordBinding>(ActivityRecordBinding::inflate), MoodSelectDialog.FeelingSelect {
    private val recordViewModel by viewModels<RecordViewModel>()
    private val moodDialog : MoodSelectDialog by lazy {
        MoodSelectDialog()
    }
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    private fun observeData(){
        recordViewModel.observeError()
        recordViewModel.recordComplete.observe(this){
            it.getContentIfNotHandled()?.let {
                moodDialog.dismiss()
                finish()
            }
        }
    }

    private fun ActivityRecordBinding.initView(){
        recordEditTextBox.setOnClickListener {
            recordEditText.requestFocus()
            this@RecordActivity.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.showSoftInput(recordEditText, InputMethodManager.SHOW_IMPLICIT)
            }
        }
        backButton.setOnClickListener {
            finish()
        }
        recordButton.setOnClickListener {
            if(recordEditText.text.isNotEmpty()){
                moodDialog.show(supportFragmentManager,"")
            }
            else
                Toast.makeText(this@RecordActivity,"내용을 입력해 주세요!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun selectFeeling(mood: Mood) {
        val content = binding.recordEditText.text.toString()
        recordViewModel.recordMood(RecordRequest(content,mood))
    }
}