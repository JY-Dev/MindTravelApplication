package com.jydev.mindtravelapplication.ui.main.consulting.store

import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemStoreBinding
import com.jydev.mindtravelapplication.domain.model.StoreItem

class StoreItemViewHolder(
    private val binding: ItemStoreBinding,
    private val purchaseItem: (item: StoreItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: StoreItem) {
        with(binding) {
            quantityTextView.text = "${item.quantity}"
            priceTextView.text = "${item.price}원"
            root.setOnClickListener {
                purchaseItem(item)
            }
        }
    }
}