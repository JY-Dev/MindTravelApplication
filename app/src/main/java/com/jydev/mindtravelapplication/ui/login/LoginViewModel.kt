package com.jydev.mindtravelapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.api.MemberApi
import com.jydev.mindtravelapplication.data.model.login.SocialLoginRequest
import com.jydev.mindtravelapplication.data.repository.LoginRepository
import com.jydev.mindtravelapplication.data.repository.MemberRepository
import com.jydev.mindtravelapplication.domain.model.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository, private val memberRepository: MemberRepository) : NetworkViewModel() {
    private val _member = MutableLiveData<Member>()
    val member : LiveData<Member>
        get() = _member
    fun socialLogin(socialLoginRequest: SocialLoginRequest){
        getApiResult({
            loginRepository.socialLogin(socialLoginRequest)
        }){
            _member.value = it
        }
    }

    fun getMember(){
        getApiResult({
            memberRepository.getMember()
        }){
            println("멤버 조회 $it")
        }
    }
}