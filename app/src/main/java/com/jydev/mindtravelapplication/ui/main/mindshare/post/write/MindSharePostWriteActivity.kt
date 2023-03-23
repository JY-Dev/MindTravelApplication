package com.jydev.mindtravelapplication.ui.main.mindshare.post.write

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.MindSharePostRequest
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostWriteBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostWriteActivity : BaseActivity<ActivityMindSharePostWriteBinding>(ActivityMindSharePostWriteBinding::inflate) {
    private val viewModel by viewModels<MindSharePostWriteViewModel>()
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    private fun observeData(){
        viewModel.observeError()
        viewModel.completePost.observe(this){
            it.getContentIfNotHandled()?.let {
                setResult(Activity.RESULT_OK, Intent())
                finish()
            }
        }
    }

    private fun ActivityMindSharePostWriteBinding.initView(){
        writeButton.setOnClickListener {
            writePost()
        }
    }

    private fun ActivityMindSharePostWriteBinding.writePost(){
        when{
            titleEditTextView.validationText() ->
                Toast.makeText(this@MindSharePostWriteActivity,"제목을 입력해주세요",Toast.LENGTH_SHORT).show()
            contentEditTextView.validationText() ->
                Toast.makeText(this@MindSharePostWriteActivity,"내용을 입력해주세요!",Toast.LENGTH_SHORT).show()
            else -> {
                val title = titleEditTextView.text.toString()
                val content = contentEditTextView.text.toString()
                val category = getCategory()
                val request = MindSharePostRequest(title,content, category)
                viewModel.writePost(request)
            }
        }
    }

    private fun getCategory() : MindSharePostCategory =
        when(binding.categoryRadioGroup.checkedRadioButtonId){
            binding.troubleCounselingRadioButton.id -> MindSharePostCategory.TROUBLE_COUNSELING
            else -> MindSharePostCategory.DAILY
        }

    private fun EditText.validationText() : Boolean{
        return this.text.isEmpty()
    }
}