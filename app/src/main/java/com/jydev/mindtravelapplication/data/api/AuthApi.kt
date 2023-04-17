package com.jydev.mindtravelapplication.data.api

import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("/v1/login/oauth2/{socialLoginType}")
    suspend fun socialLogin(
        @Path("socialLoginType") socialLoginType: String,
        @Header("Authorization") accessToken: String,
        @Header("fcm") fcmToken : String
    ) : Response<HttpResponse<TokenResponse>>

    @POST("v1/reissue/token")
    suspend fun reissueToken(
        @Header("Authorization") refreshToken : String
    ) : Response<HttpResponse<TokenResponse>>
}