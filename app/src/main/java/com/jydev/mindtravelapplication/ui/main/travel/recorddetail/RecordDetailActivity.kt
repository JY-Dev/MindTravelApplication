package com.jydev.mindtravelapplication.ui.main.travel.recorddetail

import android.os.Build
import android.widget.Toast
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityRecordDetailBinding
import com.jydev.mindtravelapplication.domain.model.MoodRecord
import java.time.format.DateTimeFormatter

class RecordDetailActivity :
    BaseActivity<ActivityRecordDetailBinding>(ActivityRecordDetailBinding::inflate) {
    lateinit var record: MoodRecord
    override fun onCreateLifeCycle() {
        binding.initView()
        getRecordData()?.setRecordData() ?: kotlin.run {
            Toast.makeText(this,"데이터가 없습니다",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun ActivityRecordDetailBinding.initView(){
        closeButton.setOnClickListener {
            finish()
        }
        deleteButton.setOnClickListener {

        }
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getRecordData() : MoodRecord? {
        return (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(RECORD_DETAIL, MoodRecord::class.java)
        } else {
            intent.getParcelableExtra(RECORD_DETAIL)
        })
    }

    private fun MoodRecord.setRecordData(){
        record = this
        val dateFormat = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일\nhh시 mm분 기록입니다.")
        binding.recordContentTextView.text = this.content
        binding.moodText.text = this.mood.detailText
        binding.dateTextView.text = this.createdDate.format(dateFormat)
    }

    companion object{
        const val RECORD_DETAIL = "record_detail"
    }
}