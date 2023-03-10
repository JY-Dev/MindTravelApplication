package com.jydev.mindtravelapplication.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jydev.mindtravelapplication.data.network.exception.RefreshTokenExpiredException
import com.jydev.mindtravelapplication.util.Event
import kotlinx.coroutines.launch

open class NetworkViewModel : ViewModel() {
    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage : LiveData<Event<String>>
        get() = _errorMessage

    fun <T> getApiResult(
        apiResult: suspend () -> T,
        success: (T) -> Unit
    ) {
        viewModelScope.launch {
            try {
                success(apiResult())
            } catch (e : Exception){
                when(e){
                    is RefreshTokenExpiredException -> {

                    }
                    else -> {
                        e.message?.let{
                            println("Error : $it")
                            _errorMessage.value = Event(it)
                        }
                    }
                }
            }
        }
    }
}