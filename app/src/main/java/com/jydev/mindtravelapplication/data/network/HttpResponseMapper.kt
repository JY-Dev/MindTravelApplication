package com.jydev.mindtravelapplication.data.network

import com.google.gson.Gson
import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.network.exception.RefreshTokenExpiredException
import retrofit2.Response

val gson = Gson()

suspend fun <R, T : Response<HttpResponse<R>>> T.getData(
    tokenRefresh: (suspend () -> Nothing)? = null,
    tokenExpired: (suspend () -> Nothing)? = null
): R {
    return if (this.isSuccessful) {
        this.body()?.data ?: throw HttpErrorCodeException(this.code(), "서버에 문제가 발생했습니다.")
    } else {
        val data = gson.fromJson(this.errorBody()?.string(), HttpResponse::class.java)
        when (this.code()) {
            403 -> tokenRefresh?.invoke() ?: tokenExpired?.invoke() ?: throw IllegalArgumentException("토큰 만료 처리 오류")
            else -> throw HttpErrorCodeException(this.code(), data.message)
        }
    }
}