package com.jydev.mindtravelapplication.ui.main.mindshare.post.like

import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindShareLikeBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostLike
import com.jydev.mindtravelapplication.util.dateFormatter
import com.jydev.mindtravelapplication.util.isToday
import com.jydev.mindtravelapplication.util.timeFormatter

class MindSharePostLikeViewHolder(private val binding : ItemMindShareLikeBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item : MindSharePostLike){
        val dateTimeFormat = if(item.createdDate.isToday()) timeFormatter else dateFormatter
        binding.createdDateTextView.text = item.createdDate.format(dateTimeFormat)
        binding.nicknameTextView.text = item.member.nickname
    }
}