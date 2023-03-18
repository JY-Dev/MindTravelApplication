package com.jydev.mindtravelapplication.ui.main.travel.recorddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MindTravelRepository
import com.jydev.mindtravelapplication.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(private val mindTravelRepository: MindTravelRepository) : NetworkViewModel() {
    private val _deleteComplete = MutableLiveData<Event<Unit>>()
    val deleteComplete : LiveData<Event<Unit>>
        get() = _deleteComplete
    fun deleteRecord(recordId: Long) {
        getApiResult({
            mindTravelRepository.deleteRecord(recordId)
        }) {
            _deleteComplete.value = Event(Unit)
        }
    }
}