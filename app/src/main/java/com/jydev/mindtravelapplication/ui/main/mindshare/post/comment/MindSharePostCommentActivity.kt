package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostCommentBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.like.MindSharePostLikeActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.like.MindSharePostLikeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostCommentActivity : BaseActivity<ActivityMindSharePostCommentBinding>(ActivityMindSharePostCommentBinding::inflate) {
    private val viewModel by viewModels<MindSharePostCommentViewModel>()
    private val likeViewModel by viewModels<MindSharePostLikeViewModel>()
    private val adapter by lazy {
        MindSharePostCommentAdapter(viewModel::isPostCreator,viewModel::isCreator,true)
    }
    override fun onCreateLifeCycle() {
        setPostDetail()
        observeData()
        binding.initView()
        viewModel.fetchComments()
        likeViewModel.fetchLikes()
    }

    private fun observeData(){
        viewModel.comments.observe(this){
            binding.commentCountTextView.text = "댓글 ${it.size}"
            adapter.setItems(it)
        }
        likeViewModel.likes.observe(this){
            binding.likeCountTextView.text = "${it.size}명이 좋아합니다."
        }
    }

    private fun ActivityMindSharePostCommentBinding.initView(){
        gotoLikeButton.setOnClickListener {
            startActivity(
                Intent(
                    this@MindSharePostCommentActivity,
                    MindSharePostLikeActivity::class.java
                ).apply {
                    putExtra(MindSharePostDetailActivity.POST_ID, viewModel.postDetail.postId)
                })
        }
        insertCommentButton.setOnClickListener {
            insertComment()
        }
        commentRecyclerView.adapter = adapter
    }

    private fun insertComment(){
        val content = binding.commentEditTextView.text.toString()
        if(content.isEmpty()){
            Toast.makeText(this,"댓글을 작성 해주세요.",Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.insertComment(content)
        binding.commentEditTextView.text.clear()
    }

    private fun setPostDetail(){
        val data = intent.let{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(MindSharePostDetailActivity.POST_DETAIL,MindSharePostDetail::class.java)
            } else {
                intent.getParcelableExtra(MindSharePostDetailActivity.POST_DETAIL)
            }
        }
        data?.let {
            likeViewModel.postId = data.postId
            viewModel.postDetail = data
        } ?: run{
            Toast.makeText(this,"글 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}