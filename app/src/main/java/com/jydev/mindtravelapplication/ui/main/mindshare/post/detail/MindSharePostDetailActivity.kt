package com.jydev.mindtravelapplication.ui.main.mindshare.post.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MindSharePostDetailActivity : BaseActivity<ActivityMindSharePostDetailBinding>(ActivityMindSharePostDetailBinding::inflate) {
    private val viewModel by viewModels<MindSharePostDetailViewModel>()
    private val dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
    override fun onCreateLifeCycle() {
        setPostId()
        viewModel.fetchMindSharePost()
        binding.initView()
        observeData()
    }

    private fun ActivityMindSharePostDetailBinding.initView(){
        backButton.setOnClickListener {
            finish()
        }
        postListButton.setOnClickListener {
            finish()
        }
    }

    private fun observeData(){
        viewModel.observeError()
        viewModel.mindSharePostDetail.observe(this){
            binding.commentCountTextView.text = "댓글 ${it.commentCount}>"
            binding.categoryTextView.text = "[${it.category.text}]"
            binding.titleTextView.text = it.title
            binding.contentTextView.text = it.content
            binding.viewCountTextView.text = it.viewCount.toString()
            binding.likeCountBottomTextView.text = it.likes.size.toString()
            binding.commentCountBottomTextView.text = it.commentCount.toString()
            binding.createdDateTextView.text = it.createdDate.format(dateTimeFormat)
        }
    }

    private fun setPostId(){
        val postId = intent.getLongExtra(POST_ID, viewModel.postId)
        if(postId == -1L){
            Toast.makeText(this,"글 정보가 없습니다.",Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.postId = postId
    }

    companion object{
        const val POST_ID = "post_id"
        const val DATE_TIME_FORMAT ="yyyy.MM.dd. HH:mm"
    }
}