package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.MindSharePostChildCommentRequest
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostCommentBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.edit.MindSharePostCommentEditActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.like.MindSharePostLikeActivity
import com.jydev.mindtravelapplication.ui.main.mindshare.post.like.MindSharePostLikeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostCommentActivity : BaseActivity<ActivityMindSharePostCommentBinding>(ActivityMindSharePostCommentBinding::inflate), MindSharePostCommentViewHolder.CommentOperator {
    private val viewModel by viewModels<MindSharePostCommentViewModel>()
    private val likeViewModel by viewModels<MindSharePostLikeViewModel>()
    private val adapter by lazy {
        MindSharePostCommentAdapter(viewModel::isPostCreator,viewModel::isCreator,true,this)
    }
    private lateinit var  launcher: ActivityResultLauncher<Intent>
    override fun onCreateLifeCycle() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                viewModel.fetchComments()
            }
        }
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

    override fun delete(commentId: Long) {
        viewModel.deleteComment(commentId)
    }

    override fun deleteChild(commentId: Long) {
        viewModel.deleteChildComment(commentId)
    }

    override fun replyComment(parentCommentId: Long, tagCommentId: Long) {

    }

    override fun edit(content: String, commentId: Long) {
        launcher.launch(Intent(this, MindSharePostCommentEditActivity::class.java).apply {
            putExtra(CONTENT,content)
            putExtra(IS_CHILD,false)
            putExtra(COMMENT_ID,commentId)
            putExtra(MindSharePostDetailActivity.POST_DETAIL,viewModel.postDetail)
        })
    }

    override fun editChild(content: String, commentId: Long) {
        launcher.launch(Intent(this,MindSharePostCommentEditActivity::class.java).apply {
            putExtra(CONTENT,content)
            putExtra(IS_CHILD,true)
            putExtra(COMMENT_ID,commentId)
            putExtra(MindSharePostDetailActivity.POST_DETAIL,viewModel.postDetail)
        })
    }

    companion object{
        const val IS_CHILD = "is_child"
        const val COMMENT_ID = "comment_id"
        const val CONTENT = "content"
    }
}