package com.jydev.mindtravelapplication.ui.main.mindshare.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jydev.mindtravelapplication.databinding.ItemMindSharePostBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePost

class MindSharePostAdapter(private val onClick : (postId : Long) -> Unit) : PagingDataAdapter<MindSharePost, MindSharePostViewHolder>(diff) {
    override fun onBindViewHolder(holder: MindSharePostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MindSharePostViewHolder {
        return MindSharePostViewHolder(onClick,ItemMindSharePostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    companion object{
        val diff = object : DiffUtil.ItemCallback<MindSharePost>() {
            override fun areItemsTheSame(oldItem: MindSharePost, newItem: MindSharePost): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: MindSharePost,
                newItem: MindSharePost
            ): Boolean {
               return oldItem.postId == newItem.postId
            }

        }
    }
}