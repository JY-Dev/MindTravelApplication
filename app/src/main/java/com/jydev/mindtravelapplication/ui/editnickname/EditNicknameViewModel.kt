package com.jydev.mindtravelapplication.ui.editnickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MemberRepository
import com.jydev.mindtravelapplication.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditNicknameViewModel @Inject constructor(private val memberRepository: MemberRepository) : NetworkViewModel() {
    private val _changeNickname = MutableLiveData<Event<Unit>>()
    val changeNickname : LiveData<Event<Unit>>
        get() = _changeNickname
    fun editNickname(nickname : String){
        getApiResult({
            memberRepository.editNickname(nickname)
        }){
            _changeNickname.value = Event(Unit)
        }
    }
}