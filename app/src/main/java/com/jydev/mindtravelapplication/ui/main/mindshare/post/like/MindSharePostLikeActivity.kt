package com.jydev.mindtravelapplication.ui.main.mindshare.post.like

import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostLikeBinding
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostLikeActivity : BaseActivity<ActivityMindSharePostLikeBinding>(ActivityMindSharePostLikeBinding::inflate) {
    private val adapter by lazy {
        MindSharePostLikeAdapter()
    }
    private val viewModel by viewModels<MindSharePostLikeViewModel>()

    override fun onCreateLifeCycle() {
        setPostId()
        binding.initView()
        viewModel.fetchLikes()
        observeData()
    }

    private fun ActivityMindSharePostLikeBinding.initView(){
        backButton.setOnClickListener {
            finish()
        }
        likeRecyclerView.adapter = adapter
    }

    private fun observeData(){
        viewModel.likes.observe(this){
            adapter.setItems(it)
            binding.likeCountTextView.text = "좋아요 ${it.size}"
        }
    }

    private fun setPostId(){
        val postId = intent.getLongExtra(MindSharePostDetailActivity.POST_ID, viewModel.postId)
        if(postId == -1L){
            Toast.makeText(this,"글 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.postId = postId
    }
}