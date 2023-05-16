package com.jydev.mindtravelapplication.fcm

data class FcmPayLoad(val fcmService: FcmService, val title : String, val content : String, val isContainData : Boolean)