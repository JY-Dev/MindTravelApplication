package com.jydev.mindtravelapplication.ui.main.travel.recordlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MindTravelRepository
import com.jydev.mindtravelapplication.domain.model.MoodRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordListViewModel @Inject constructor(private val mindTravelRepository: MindTravelRepository) : NetworkViewModel() {
    private val _recordList = MutableLiveData<List<MoodRecord>>()
    val recordList : LiveData<List<MoodRecord>>
        get() = _recordList
    fun fetchRecordList(date : String){
        getApiResult({
            mindTravelRepository.fetchRecordList(date)
        }){
            _recordList.value = it
        }
    }
}