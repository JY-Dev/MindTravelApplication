package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindShareCommentBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostChildComment
import com.jydev.mindtravelapplication.domain.model.MindSharePostComment
import com.jydev.mindtravelapplication.domain.model.PostComment
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity
import java.time.format.DateTimeFormatter

class MindSharePostCommentViewHolder(
    private val isPostCreator : (memberId : Long) -> Boolean,
    private val isCreator : (memberId : Long) -> Boolean,
    private val isEditMode : Boolean,
    private val binding: ItemMindShareCommentBinding,
    private val commentOperator: CommentOperator?,
) : RecyclerView.ViewHolder(binding.root) {
    private val dateFormat =
        DateTimeFormatter.ofPattern(MindSharePostDetailActivity.DATE_TIME_FORMAT)

    fun bind(item: PostComment) {
        with(binding) {
            viewComment.contentTextView.text = item.content
            viewComment.createdDateTextView.text = item.createdDate.format(dateFormat)
            viewComment.nicknameTextView.text = item.member.nickname
            viewComment.creatorText.visibility = if(isPostCreator(item.member.id)) View.VISIBLE else View.INVISIBLE
            viewComment.editGroup.visibility = if(isCreator(item.member.id) && isEditMode) View.VISIBLE else View.INVISIBLE
            viewComment.commentGroup.visibility = View.VISIBLE
            viewComment.deletedText.visibility = View.GONE
            viewComment.commentGroup.visibility = View.VISIBLE
            when(item){
                is MindSharePostComment -> {
                    val adapter = MindSharePostCommentAdapter(isPostCreator, isCreator, isEditMode,commentOperator)
                    viewComment.tagNicknameTextView.visibility = View.GONE
                    childCommentRecyclerView.adapter = adapter
                    adapter.setItems(item.childComments)
                    viewComment.replyButton.setOnClickListener {
                        commentOperator?.replyComment(item.content,item.commentId,-1L)
                    }
                    viewComment.deleteButton.setOnClickListener {
                        commentOperator?.delete(item.commentId)
                    }
                    viewComment.editButton.setOnClickListener {
                        commentOperator?.edit(item.content,item.commentId)
                    }

                    if(item.isDeleted){
                        viewComment.creatorText.visibility = View.INVISIBLE
                        viewComment.deletedText.visibility = View.VISIBLE
                        viewComment.commentGroup.visibility =  View.INVISIBLE
                        viewComment.editGroup.visibility = View.INVISIBLE
                    }

                }
                is MindSharePostChildComment -> {
                    viewComment.tagNicknameTextView.visibility = if(item.tagNickname.isEmpty()) View.GONE else View.VISIBLE
                    viewComment.tagNicknameTextView.text = item.tagNickname
                    viewComment.replyButton.setOnClickListener {
                        commentOperator?.replyComment(item.content,item.parentCommentId,item.member.id)
                    }
                    viewComment.deleteButton.setOnClickListener {
                        commentOperator?.deleteChild(item.commentId)
                    }
                    viewComment.editButton.setOnClickListener {
                        commentOperator?.editChild(item.content,item.commentId)
                    }
                }
            }
        }
    }

    interface CommentOperator{
        fun delete(commentId : Long)
        fun deleteChild(commentId : Long)
        fun replyComment(content : String, parentCommentId : Long, tagMemberId : Long)
        fun edit(content : String, commentId: Long)
        fun editChild(content : String, commentId: Long)
    }
}