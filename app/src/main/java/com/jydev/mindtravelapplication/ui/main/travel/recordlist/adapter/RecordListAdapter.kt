package com.jydev.mindtravelapplication.ui.main.travel.recordlist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemRecordListBinding
import com.jydev.mindtravelapplication.domain.model.MoodRecord

class RecordListAdapter : RecyclerView.Adapter<RecordListViewHolder>() {
    private var list = listOf<MoodRecord>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListViewHolder {
        return RecordListViewHolder(
            ItemRecordListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: RecordListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list : List<MoodRecord>){
        this.list = list
        notifyDataSetChanged()
    }
}