package com.jydev.mindtravelapplication.ui.main.mindshare.post.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import com.jydev.mindtravelapplication.domain.model.MindSharePostLike
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostLikeViewModel.Companion.isLikeClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostDetailViewModel @Inject constructor(
    private val mindShareRepository: MindShareRepository,
    private val loginPreference: LoginPreference
) : NetworkViewModel() {
    private val _isLikeClicked = MutableLiveData<Pair<List<MindSharePostLike> ,Boolean>>()
    val isLikeClicked : LiveData<Pair<List<MindSharePostLike> ,Boolean>>
        get() = _isLikeClicked
    private val _mindSharePostDetail = MutableLiveData<MindSharePostDetail>()
    val mindSharePostDetail: LiveData<MindSharePostDetail>
        get() = _mindSharePostDetail
    var postId = -1L
    fun fetchMindSharePost() {
        getApiResult({
            mindShareRepository.fetchMindSharePost(postId)
        }) {
            _mindSharePostDetail.value = it
            val memberIdx = loginPreference.getMember()?.memberIdx ?: -1L
            _isLikeClicked.value = it.likes to it.likes.isLikeClicked(memberIdx)
        }
    }

    fun clickLike(){
        val isLikeClicked = _isLikeClicked.value?.second ?: false
        getApiResult({
            if(isLikeClicked)
                mindShareRepository.deleteMindSharePostLike(postId)
            else
                mindShareRepository.mindSharePostLike(postId)
        }){
            val memberIdx = loginPreference.getMember()?.memberIdx ?: -1L
            _isLikeClicked.value = it to it.isLikeClicked(memberIdx)
        }
    }

    fun isCreator(memberId: Long): Boolean {
        return _mindSharePostDetail.value?.let {
            memberId == it.member.id
        } ?: false
    }
}