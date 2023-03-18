package com.jydev.mindtravelapplication.ui.main.travel.recordlist

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.view.View
import android.widget.DatePicker
import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityRecordListBinding
import com.jydev.mindtravelapplication.ui.main.travel.recorddetail.RecordDetailActivity
import com.jydev.mindtravelapplication.ui.main.travel.recordlist.adapter.RecordListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class RecordListActivity :
    BaseActivity<ActivityRecordListBinding>(ActivityRecordListBinding::inflate), OnDateSetListener {
    private var currentLocalDate = LocalDate.now()
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val recordListViewModel by viewModels<RecordListViewModel>()
    private val recordListAdapter by lazy {
        RecordListAdapter {
            startActivity(Intent(this,RecordDetailActivity::class.java).apply {
                putExtra(RecordDetailActivity.RECORD_DETAIL,it)
            })
        }
    }
    override fun onCreateLifeCycle() {
        binding.initView()
        updateDate()
        observeData()
    }

    private fun observeData(){
        recordListViewModel.recordList.observe(this){
            binding.emptyListText.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            recordListAdapter.updateList(it)
        }
        recordListViewModel.observeError()
    }

    private fun ActivityRecordListBinding.initView() {
        backButton.setOnClickListener {
            finish()
        }
        calendarButton.setOnClickListener {
            val year = currentLocalDate.year
            val month = currentLocalDate.month.value-1
            val day = currentLocalDate.dayOfMonth
            val datePickerDialog =
                DatePickerDialog(this@RecordListActivity, this@RecordListActivity, year, month, day)
            datePickerDialog.show()
        }
        recordRecyclerview.adapter = recordListAdapter
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        currentLocalDate = LocalDate.of(year, month+1, day)
        updateDate()
    }

    private fun updateDate() {
        val date = dateFormat.format(currentLocalDate)
        recordListViewModel.fetchRecordList(date)
        binding.dateTextView.text = date
    }
}