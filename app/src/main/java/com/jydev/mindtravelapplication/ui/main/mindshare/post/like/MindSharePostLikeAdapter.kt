package com.jydev.mindtravelapplication.ui.main.mindshare.post.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemMindShareLikeBinding
import com.jydev.mindtravelapplication.domain.model.MindSharePostLike

class MindSharePostLikeAdapter : RecyclerView.Adapter<MindSharePostLikeViewHolder>() {
    private var items = emptyList<MindSharePostLike>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MindSharePostLikeViewHolder {
        return MindSharePostLikeViewHolder(
            ItemMindShareLikeBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: MindSharePostLikeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<MindSharePostLike>) {
        this.items = items
        notifyDataSetChanged()
    }
}