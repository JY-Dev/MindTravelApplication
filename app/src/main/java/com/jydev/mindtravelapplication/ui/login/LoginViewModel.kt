package com.jydev.mindtravelapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.model.login.SocialLoginRequest
import com.jydev.mindtravelapplication.data.repository.LoginRepository
import com.jydev.mindtravelapplication.domain.model.MemberRole
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : NetworkViewModel() {
    private val _moveScreen = MutableLiveData<MoveScreen>()
    val moveScreen: LiveData<MoveScreen>
        get() = _moveScreen

    fun socialLogin(socialLoginRequest: SocialLoginRequest) {
        getApiResult({
            loginRepository.socialLogin(socialLoginRequest)
        }) {
            if(it.role == MemberRole.NEW_USER)
                _moveScreen.value = MoveScreen.EditNicknameScreen
            else
                _moveScreen.value = MoveScreen.MainScreen
        }
    }

    sealed class MoveScreen {
        object EditNicknameScreen : MoveScreen()
        object MainScreen : MoveScreen()
    }
}