package com.jydev.mindtravelapplication.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.data.repository.AuthRepository
import com.jydev.mindtravelapplication.domain.model.MemberLogin
import com.jydev.mindtravelapplication.domain.model.MemberRole
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val loginPreference: LoginPreference, private val authRepository: AuthRepository) : NetworkViewModel() {
    private val _moveScreen = MutableLiveData<MoveScreen>()
    val moveScreen : LiveData<MoveScreen>
        get() = _moveScreen
    fun moveScreen(){
        loginPreference.getMember()?.let {
            validationLogin(it)
        } ?: run{
            _moveScreen.value = MoveScreen.LoginScreen
        }
    }

    fun validationLogin(member : MemberLogin){
        getApiResult({
            authRepository.reissueToken()
        }){
            if(member.role == MemberRole.NEW_USER)
                _moveScreen.value = MoveScreen.EditNicknameScreen
            else
                _moveScreen.value = MoveScreen.MainScreen
        }
    }

    sealed class MoveScreen{
        object EditNicknameScreen : MoveScreen()
        object MainScreen : MoveScreen()
        object LoginScreen : MoveScreen()
    }
}