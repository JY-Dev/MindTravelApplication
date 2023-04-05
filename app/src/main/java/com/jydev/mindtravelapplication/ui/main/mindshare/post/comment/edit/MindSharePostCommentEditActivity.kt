package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.edit

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityMindSharePostCommentEditBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.COMMENT_ID
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.CONTENT
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentActivity.Companion.IS_CHILD
import com.jydev.mindtravelapplication.ui.main.mindshare.post.comment.MindSharePostCommentViewModel
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MindSharePostCommentEditActivity :
    BaseActivity<ActivityMindSharePostCommentEditBinding>(ActivityMindSharePostCommentEditBinding::inflate) {

    private val commentViewModel by viewModels<MindSharePostCommentViewModel>()
    override fun onCreateLifeCycle() {
        setPostDetail()
        binding.initView()
        observeData()
    }

    private fun observeData() {
        commentViewModel.observeError()
        commentViewModel.comments.observe(this){
            Toast.makeText(this,"댓글 수정이 완료 되었습니다.",Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun ActivityMindSharePostCommentEditBinding.initView(){
        editButton.setOnClickListener {
            editComment()
        }
        commentTextView.text = intent.getStringExtra(CONTENT)
        contentEditTextView.setText(intent.getStringExtra(CONTENT))
    }

    private fun editComment(){
        val content = binding.contentEditTextView.text.toString()
        if(content.isEmpty()){
            Toast.makeText(this,"내용을 입력 해주세요.",Toast.LENGTH_SHORT).show()
            return
        }
        val isChildComment = intent.getBooleanExtra(IS_CHILD,false)
        val commentId = intent.getLongExtra(COMMENT_ID,-1L).also {
            if(it == -1L){
                Toast.makeText(this,"댓글 정보가 없습니다.",Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        }
        if(isChildComment){
            commentViewModel.editChildComment(content,commentId)
        } else{
            commentViewModel.editComment(content,commentId)
        }
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