package com.jydev.mindtravelapplication.ui.main.mindshare.post.detail

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.jydev.mindtravelapplication.R
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostDetailBinding
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentAdapter
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MindSharePostDetailActivity :
    BaseActivity<ActivityMindSharePostDetailBinding>(ActivityMindSharePostDetailBinding::inflate) {
    private val viewModel by viewModels<MindSharePostDetailViewModel>()
    private val commentViewModel by viewModels<MindSharePostCommentViewModel>()
    private val dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
    private val commentAdapter by lazy {
        MindSharePostCommentAdapter(
            viewModel::isCreator,
            commentViewModel::isCreator,
            false
        )
    }

    override fun onCreateLifeCycle() {
        setPostId()
        viewModel.fetchMindSharePost()
        binding.initView()
        observeData()
    }

    private fun ActivityMindSharePostDetailBinding.initView() {
        backButton.setOnClickListener {
            finish()
        }
        postListButton.setOnClickListener {
            finish()
        }
        likeButton.setOnClickListener {
            viewModel.clickLike()
        }
        swipeLayout.setOnRefreshListener {
            viewModel.fetchMindSharePost()
            swipeLayout.isRefreshing = false
        }
        commentImage.setOnClickListener {
            startActivity(
                Intent(
                    this@MindSharePostDetailActivity,
                    MindSharePostCommentActivity::class.java
                ).apply {
                    putExtra(POST_DETAIL,viewModel.mindSharePostDetail.value)
                })
        }
        commentRecyclerView.adapter = commentAdapter
    }

    private fun observeData() {
        viewModel.observeError()
        viewModel.mindSharePostDetail.observe(this) {
            commentAdapter.setItems(it.comments)
            binding.commentCountTextView.text = "댓글 ${it.commentCount}>"
            binding.categoryTextView.text = "[${it.category.text}]"
            binding.titleTextView.text = it.title
            binding.contentTextView.text = it.content
            binding.viewCountTextView.text = it.viewCount.toString()
            binding.commentCountBottomTextView.text = it.commentCount.toString()
            binding.createdDateTextView.text = it.createdDate.format(dateTimeFormat)
        }
        viewModel.isLikeClicked.observe(this) {
            binding.likeCountBottomTextView.text = it.first.size.toString()
            val imageDrawableId = if (it.second) R.drawable.fill_heart else R.drawable.heart
            binding.likeButton.background = ContextCompat.getDrawable(this, imageDrawableId)
        }
    }

    private fun setPostId() {
        val postId = intent.getLongExtra(POST_ID, viewModel.postId)
        if (postId == -1L) {
            Toast.makeText(this, "글 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.postId = postId
    }

    companion object {
        const val POST_ID = "post_id"
        const val POST_DETAIL = "POST_DETAIL"
        const val DATE_TIME_FORMAT = "yyyy.MM.dd. HH:mm"
    }
}