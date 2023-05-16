package com.jydev.mindtravelapplication.fcm

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.jydev.mindtravelapplication.fcm.data.MindShareFcmData
import com.jydev.mindtravelapplication.ui.main.mindshare.post.detail.MindSharePostDetailActivity

enum class FcmService {
    MIND_SHARE {
        override val dataClass: Class<*>
            get() = MindShareFcmData::class.java
    };

    fun getIntent(context: Context, payLoad: FcmPayLoad, jsonData: String): Intent {
        val data = if (payLoad.isContainData) gson.fromJson(
            jsonData,
            payLoad.fcmService.dataClass
        ) else null

        return when (this) {
            MIND_SHARE -> {
                MindSharePostDetailActivity.getFcmIntent(context, data as? MindShareFcmData)
            }
        }
    }

    abstract val dataClass: Class<*>

    companion object {
        val gson = Gson()
    }
}