package com.jydev.mindtravelapplication.ui.main.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MemberRepository
import com.jydev.mindtravelapplication.domain.model.MemberLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(private val memberRepository: MemberRepository) : NetworkViewModel() {
    private val _memberLogin = MutableLiveData<MemberLogin>()
    val memberLogin : LiveData<MemberLogin>
        get() = _memberLogin
    fun getMember(){
        getApiResult({
            memberRepository.getMember()
        }){
            _memberLogin.value = it
        }
    }
}