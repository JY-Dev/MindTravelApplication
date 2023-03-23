package com.jydev.mindtravelapplication.ui.main.mindshare.post

import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindSharePostBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePost

class MindSharePostViewHolder(private val onClick : (postId : Long) -> Unit,private val binding: ItemMindSharePostBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MindSharePost?) {
        item?.let {
            with(binding) {
                nicknameTextView.text = item.nickname
                viewCountTextView.text = item.viewCount.toString()
                likeCountTextView.text = item.likeCount.toString()
                titleTextView.text = item.title
                root.setOnClickListener {
                    onClick(item.postId)
                }
            }
        }

    }
}