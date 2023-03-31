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
    private val binding: ItemMindShareCommentBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val dateFormat =
        DateTimeFormatter.ofPattern(MindSharePostDetailActivity.DATE_TIME_FORMAT)

    fun bind(item: PostComment) {
        with(binding) {
            viewComment.contentTextView.text = item.content
            viewComment.createdDateTextView.text = item.createdDate.format(dateFormat)
            viewComment.nicknameTextView.text = item.member.nickname
            viewComment.editGroup.visibility = if(isCreator(item.member.id) && isEditMode) View.VISIBLE else View.INVISIBLE
            viewComment.creatorText.visibility = if(isPostCreator(item.member.id)) View.VISIBLE else View.INVISIBLE
            when(item){
                is MindSharePostComment -> {
                    val adapter = MindSharePostCommentAdapter(isPostCreator, isCreator, isEditMode)
                    viewComment.tagNicknameTextView.visibility = View.GONE
                    childCommentRecyclerView.adapter = adapter
                    adapter.setItems(item.childComments)
                }
                is MindSharePostChildComment -> {
                    viewComment.tagNicknameTextView.visibility = if(item.tagNickname.isEmpty()) View.GONE else View.VISIBLE
                    viewComment.tagNicknameTextView.text = item.tagNickname
                }
            }
        }
    }
}