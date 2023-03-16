package com.jydev.mindtravelapplication.ui.main.travel.record

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityRecordBinding

class RecordActivity : BaseActivity<ActivityRecordBinding>(ActivityRecordBinding::inflate) {

    override fun onCreateLifeCycle() {
        binding.initView()
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
    }
}