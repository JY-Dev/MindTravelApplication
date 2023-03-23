package com.jydev.mindtravelapplication.ui.main.mindshare.post.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.model.MindSharePostRequest
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostWriteViewModel @Inject constructor(private val mindShareRepository: MindShareRepository) : NetworkViewModel() {
    private val _completePost = MutableLiveData<Event<Unit>>()
    val completePost : LiveData<Event<Unit>>
        get() = _completePost

    fun writePost(request : MindSharePostRequest){
        getApiResult({
            mindShareRepository.postMindSharePost(request)
        }){
            _completePost.value = Event(Unit)
        }
    }
}