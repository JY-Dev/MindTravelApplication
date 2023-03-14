package com.jydev.mindtravelapplication.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.domain.model.MemberRole
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val loginPreference: LoginPreference) : ViewModel() {
    private val _moveScreen = MutableLiveData<MoveScreen>()
    val moveScreen : LiveData<MoveScreen>
        get() = _moveScreen
    fun moveScreen(){
        loginPreference.getMember()?.let {
            if(it.role == MemberRole.NEW_USER)
                _moveScreen.value = MoveScreen.EditNicknameScreen
            else
                _moveScreen.value = MoveScreen.MainScreen
        } ?: run{
            _moveScreen.value = MoveScreen.LoginScreen
        }
    }

    sealed class MoveScreen{
        object EditNicknameScreen : MoveScreen()
        object MainScreen : MoveScreen()
        object LoginScreen : MoveScreen()
    }
}