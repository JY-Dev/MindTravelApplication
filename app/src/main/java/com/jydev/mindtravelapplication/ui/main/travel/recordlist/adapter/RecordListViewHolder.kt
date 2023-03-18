package com.jydev.mindtravelapplication.ui.main.travel.recordlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jydev.mindtravelapplication.databinding.ItemRecordListBinding
import com.jydev.mindtravelapplication.domain.model.MoodRecord
import java.time.format.DateTimeFormatter

class RecordListViewHolder(val binding : ItemRecordListBinding) : RecyclerView.ViewHolder(binding.root) {
    private val timeFormat = DateTimeFormatter.ofPattern("hh:mm")
    fun bind(item : MoodRecord){
        with(binding){
            timeTextView.text = item.createdDate.format(timeFormat)
            moodTextView.text = "기분 ${item.mood.moodText}"
        }
    }
}