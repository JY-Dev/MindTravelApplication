package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindShareCommentBinding
import com.jydev.mindtravelapplication.domain.model.PostComment

class MindSharePostCommentAdapter(
    private val isPostCreator: (memberId: Long) -> Boolean,
    private val isCommentCreator: (memberId: Long) -> Boolean,
    private val isEditMode: Boolean
) : RecyclerView.Adapter<MindSharePostCommentViewHolder>() {
    private var items : List<PostComment> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MindSharePostCommentViewHolder {
        return MindSharePostCommentViewHolder(
            isPostCreator,
            isCommentCreator,
            isEditMode,
            ItemMindShareCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: MindSharePostCommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items : List<PostComment>){
        this.items = items
        notifyDataSetChanged()
    }
}