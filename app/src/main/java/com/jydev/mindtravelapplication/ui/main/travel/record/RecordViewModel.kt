package com.jydev.mindtravelapplication.ui.main.travel.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.model.RecordRequest
import com.jydev.mindtravelapplication.data.repository.MindTravelRepository
import com.jydev.mindtravelapplication.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(private val mindTravelRepository : MindTravelRepository) : NetworkViewModel(){
    private val _recordComplete = MutableLiveData<Event<Unit>>()
    val recordComplete : LiveData<Event<Unit>>
        get() = _recordComplete
    fun recordMood(recordRequest: RecordRequest){
        getApiResult({
            mindTravelRepository.recordMood(recordRequest)
        }){
            _recordComplete.value = Event(Unit)
        }
    }
}