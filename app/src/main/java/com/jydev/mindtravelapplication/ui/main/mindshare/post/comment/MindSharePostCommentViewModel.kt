package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.model.MindSharePostChildCommentRequest
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import com.jydev.mindtravelapplication.domain.model.MindSharePostComment
import com.jydev.mindtravelapplication.domain.model.MindSharePostDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostCommentViewModel @Inject constructor(
    private val mindShareRepository: MindShareRepository,
    private val loginPreference: LoginPreference
) : NetworkViewModel() {

    private val _comments = MutableLiveData<List<MindSharePostComment>>()
    val comments: LiveData<List<MindSharePostComment>>
        get() = _comments
    lateinit var postDetail : MindSharePostDetail
    fun isCreator(creatorId: Long): Boolean {
        return creatorId == loginPreference.getMember()?.memberIdx
    }

    fun isPostCreator(memberId: Long): Boolean {
        return postDetail.member.id == memberId
    }

    fun fetchComments() {
        getApiResult({
            mindShareRepository.fetchPostComments(postDetail.postId)
        }) {
            _comments.value = it
        }
    }

    fun insertComment(content : String){
        getApiResult({
            mindShareRepository.insertPostComment(content,postDetail.postId)
        }){
            _comments.value = it
        }
    }

    fun deleteComment(commentId : Long){
        getApiResult({
            mindShareRepository.deletePostComment(postDetail.postId, commentId)
        }){
            _comments.value = it
        }
    }

    fun editComment(content : String,commentId : Long){
        getApiResult({
            mindShareRepository.editPostComment(content,commentId,postDetail.postId)
        }){
            _comments.value = it
        }
    }

    fun insertChildComment(request : MindSharePostChildCommentRequest){
        getApiResult({
            mindShareRepository.insertPostChildComment(request,postDetail.postId)
        }){
            _comments.value = it
        }
    }

    fun deleteChildComment(commentId : Long){
        getApiResult({
            mindShareRepository.deletePostChildComment(postDetail.postId, commentId)
        }){
            _comments.value = it
        }
    }

    fun editChildComment(content : String, commentId : Long){
        getApiResult({
            mindShareRepository.editPostChildComment(content,commentId,postDetail.postId)
        }){
            _comments.value = it
        }
    }
}