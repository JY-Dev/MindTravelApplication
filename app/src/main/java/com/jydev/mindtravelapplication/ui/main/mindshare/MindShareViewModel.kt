package com.jydev.mindtravelapplication.ui.main.mindshare

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jydev.mindtravelapplication.util.SingleLiveEvent

class MindShareViewModel : ViewModel() {
    private val _postEvent = SingleLiveEvent<Unit>()
    val postEvent: LiveData<Unit>
        get() = _postEvent

    fun sendPostEvent() {
        _postEvent.value = Unit
    }
}