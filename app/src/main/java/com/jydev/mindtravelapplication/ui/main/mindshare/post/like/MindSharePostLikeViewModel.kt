package com.jydev.mindtravelapplication.ui.main.mindshare.post.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.domain.model.MindSharePostLike
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostLikeViewModel @Inject constructor(private val repository: MindShareRepository) : NetworkViewModel() {
    private val _likes = MutableLiveData<List<MindSharePostLike>>()
    val likes : LiveData<List<MindSharePostLike>>
        get() = _likes
    var postId : Long = -1L

    fun fetchLikes(){
        getApiResult({
            repository.fetchPostLikes(postId)
        }){
            _likes.value = it
        }
    }

    companion object {
        fun List<MindSharePostLike>.isLikeClicked(memberIdx: Long): Boolean {
            return this.none {
                it.member.id == memberIdx
            }.not()
        }
    }
}