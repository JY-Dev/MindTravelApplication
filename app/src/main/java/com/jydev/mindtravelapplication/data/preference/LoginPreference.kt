package com.jydev.mindtravelapplication.data.preference

import android.content.Context
import com.google.gson.Gson
import com.jydev.mindtravelapplication.domain.model.Member
import com.jydev.mindtravelapplication.domain.model.Token
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginPreference @Inject constructor(
    @ApplicationContext private val context : Context
) {
    private val sharedPref = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveToken(token : Token){
        val tokenJson = gson.toJson(token)
        sharedPref.edit()
            .putString(TOKEN,tokenJson)
            .apply()
    }

    fun getToken() : Token?{
        val tokenJson = sharedPref.getString(TOKEN,null)
        tokenJson?.let {
            return gson.fromJson(it,Token::class.java)
        } ?: run {
            return null
        }
    }

    fun saveMember(member : Member){
        val tokenJson = gson.toJson(member)
        sharedPref.edit()
            .putString(MEMBER,tokenJson)
            .apply()
    }

    fun getMember() : Member?{
        val memberJson = sharedPref.getString(MEMBER,null)
        memberJson?.let {
            return gson.fromJson(it,Member::class.java)
        } ?: run {
            return null
        }
    }

    fun clearData(){
        sharedPref.edit()
            .clear()
            .apply()
    }

    companion object{
        const val KEY = "login"
        const val TOKEN = "login_token"
        const val MEMBER = "login_member"
    }
}