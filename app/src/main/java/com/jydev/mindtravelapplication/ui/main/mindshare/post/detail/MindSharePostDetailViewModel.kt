package com.jydev.mindtravelapplication.ui.main.mindshare.post.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostDetailViewModel @Inject constructor(private val mindShareRepository: MindShareRepository) : NetworkViewModel() {
    private val _mindSharePostDetail = MutableLiveData<MindSharePostDetail>()
    val mindSharePostDetail : LiveData<MindSharePostDetail>
        get() = _mindSharePostDetail
    var postId = -1L
    fun fetchMindSharePost(){
        getApiResult({
            mindShareRepository.fetchMindSharePost(postId)
        },_mindSharePostDetail::setValue)
    }

    fun isCreator(memberId : Long) : Boolean{
        return _mindSharePostDetail.value?.let{
            memberId == it.member.id
        } ?: false
    }
}