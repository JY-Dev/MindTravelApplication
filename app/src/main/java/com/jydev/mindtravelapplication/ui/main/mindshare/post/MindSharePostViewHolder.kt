package com.jydev.mindtravelapplication.ui.main.mindshare.post

import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindSharePostBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePost
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MindSharePostViewHolder(private val onClick : (postId : Long) -> Unit,private val binding: ItemMindSharePostBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    fun bind(item: MindSharePost?) {
        item?.let {
            with(binding) {
                nicknameTextView.text = item.member.nickname
                viewCountTextView.text = item.viewCount.toString()
                likeCountTextView.text = item.likeCount.toString()
                commentCountTextView.text = item.commentCount.toString()
                titleTextView.text = item.title
                val dateTimeFormat = if(item.createdDate.isToday()) timeFormatter else dateFormatter
                dateTimeTextView.text = item.createdDate.format(dateTimeFormat)
                root.setOnClickListener {
                    onClick(item.postId)
                }
            }
        }
    }

    private fun LocalDateTime.isToday() : Boolean{
        val cur = LocalDateTime.now()
        return cur.year == this.year && cur.month == this.month && cur.dayOfMonth == this.dayOfMonth
    }

}