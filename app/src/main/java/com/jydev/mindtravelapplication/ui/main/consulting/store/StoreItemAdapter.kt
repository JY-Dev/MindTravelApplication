package com.jydev.mindtravelapplication.ui.main.consulting.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.jydev.mindtravelapplication.databinding.ItemStoreBinding
import com.jydev.mindtravelapplication.domain.model.StoreItem

class StoreItemAdapter(private val purchaseItem: (item: StoreItem) -> Unit) : Adapter<StoreItemViewHolder>() {
    private var items = emptyList<StoreItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            ItemStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), purchaseItem
        )
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(items: List<StoreItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}