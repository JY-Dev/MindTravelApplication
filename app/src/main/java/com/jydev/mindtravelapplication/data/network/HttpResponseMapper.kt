package com.jydev.mindtravelapplication.data.network

import com.google.gson.Gson
import com.jydev.mindtravelapplication.data.model.HttpResponse
import com.jydev.mindtravelapplication.data.network.exception.RefreshTokenExpiredException
import com.jydev.mindtravelapplication.data.network.exception.TokenExpiredException
import retrofit2.HttpException
import retrofit2.Response

val gson = Gson()

fun <R,T : HttpResponse<R>> T.getData() : R{
    return try {
        this.data ?: throw HttpErrorCodeException(this.code,"서버에 문제가 발생했습니다.")
    } catch (e : HttpException){
        when(e.code()){
            401 -> throw TokenExpiredException()
            403 -> throw RefreshTokenExpiredException()
            else -> throw HttpErrorCodeException(this.code,this.message)
        }
    }
    /*return if(this.isSuccessful){
        this.data ?: throw HttpErrorCodeException(this.code(),"서버에 문제가 발생했습니다.")
    } else{
        val errorJson = this.errorBody()?.string() ?: throw HttpErrorCodeException(this.code(),"서버에 문제가 발생했습니다.")
        val codeMessage = gson.fromJson(errorJson, CodeMessageResponse::class.java)
        when(this.code()){
            401 -> throw TokenExpiredException()
            403 -> throw RefreshTokenExpiredException()
            else -> throw HttpErrorCodeException(codeMessage.code,codeMessage.message)
        }
    }*/
}