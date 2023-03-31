package com.jydev.mindtravelapplication.ui.main.mindshare.post.comment

import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.preference.LoginPreference
import com.jydev.mindtravelapplication.data.repository.MindShareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MindSharePostCommentViewModel @Inject constructor(
    private val mindShareRepository: MindShareRepository,
    private val loginPreference: LoginPreference
) : NetworkViewModel() {

    fun isCreator(creatorId: Long): Boolean {
        return creatorId == loginPreference.getMember()?.memberIdx
    }

}