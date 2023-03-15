package com.jydev.mindtravelapplication.ui.main.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MemberRepository
import com.jydev.mindtravelapplication.domain.model.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(private val memberRepository: MemberRepository) : NetworkViewModel() {
    private val _member = MutableLiveData<Member>()
    val member : LiveData<Member>
        get() = _member
    fun getMember(){
        getApiResult({
            memberRepository.getMember()
        }){
            _member.value = it
        }
    }
}