package com.jydev.mindtravelapplication.ui.main.mindshare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MindShareViewModel : ViewModel() {
    private val _postEvent = MutableSharedFlow<Unit>()
    val postEvent: Flow<Unit>
        get() = _postEvent

    fun sendPostEvent() {
        viewModelScope.launch {
            _postEvent.emit(Unit)
        }
    }
}