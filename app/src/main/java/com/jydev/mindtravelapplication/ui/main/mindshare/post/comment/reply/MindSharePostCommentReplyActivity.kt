package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.reply

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.MindSharePostChildCommentRequest
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostCommentReplyBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.CONTENT
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.PARENT_COMMENT_ID
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.TAG_MEMBER_ID
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentViewModel
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostCommentReplyActivity : BaseActivity<ActivityMindSharePostCommentReplyBinding>(ActivityMindSharePostCommentReplyBinding::inflate) {
    private val commentViewModel by viewModels<MindSharePostCommentViewModel>()
    override fun onCreateLifeCycle() {
        setPostDetail()
        binding.initView()
        observeData()
    }

    private fun ActivityMindSharePostCommentReplyBinding.initView(){
        commentTextView.text = intent.getStringExtra(CONTENT)
        registerButton.setOnClickListener {
            registerReplyComment()
        }
    }

    private fun observeData(){
        commentViewModel.observeError()
        commentViewModel.comments.observe(this){
            Toast.makeText(this,"답글 작성이 완료 되었습니다.",Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun registerReplyComment(){
        val content = binding.contentEditTextView.text.toString()
        val tagMemberId = intent.getLongExtra(TAG_MEMBER_ID,-1L)
        val commentId = intent.getLongExtra(PARENT_COMMENT_ID,-1L).also {
            if(it == -1L){
                Toast.makeText(this,"댓글 정보가 없습니다.",Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        }
        val postId = commentViewModel.postDetail.postId
        if(content.isEmpty()){
            Toast.makeText(this,"내용을 입력해주세요.",Toast.LENGTH_SHORT).show()
            return
        }
        val request = MindSharePostChildCommentRequest(
            content,
            postId,
            tagMemberId,
            commentId
        )
        commentViewModel.insertChildComment(request)
    }

    private fun setPostDetail(){
        val data = intent.let{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(
                    MindSharePostDetailActivity.POST_DETAIL,
                    MindSharePostDetail::class.java)
            } else {
                intent.getParcelableExtra(MindSharePostDetailActivity.POST_DETAIL)
            }
        }
        data?.let {
            commentViewModel.postDetail = data
        } ?: run{
            Toast.makeText(this,"글 정보가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}