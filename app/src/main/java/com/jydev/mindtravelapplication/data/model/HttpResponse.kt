package com.jydev.mindtravelapplication.data.model

data class HttpResponse<T>(val code : Int, val message : String, val data : T?)