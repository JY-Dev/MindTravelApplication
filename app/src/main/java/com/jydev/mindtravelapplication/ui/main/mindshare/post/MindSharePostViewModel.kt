package com.jydev.mindtravelapplication.ui.main.mindshare.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.model.MindSharePostsRequest
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.domain.model.MindSharePost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MindSharePostViewModel @Inject constructor(private val repository: MindShareRepository) :
    NetworkViewModel() {
    private val _posts = MutableLiveData<PagingData<MindSharePost>>()
    val posts: LiveData<PagingData<MindSharePost>>
        get() = _posts

    fun fetchMindSharePosts(request: MindSharePostsRequest) {
        viewModelScope.launch {
            repository.fetchMindSharePosts(request)
                .cachedIn(viewModelScope)
                .flowErrorHandling().collect {
                    _posts.value = it
                }
        }
    }
}