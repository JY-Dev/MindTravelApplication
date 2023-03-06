package com.jydev.mindtravelapplication.ui

import android.content.Intent
import android.os.Build
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.jydev.mindtravelapplication.BuildConfig
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreateLifeCycle() {
        initGoogleLoginSetting()
        binding.initView()
    }

    private fun ActivityLoginBinding.initView(){
        googleLoginButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startForResult.launch(signInIntent)
        }
    }

    override fun activityResultCallback(data : Intent){
        Log.d("Login","로그인 입니다!!! ${GoogleSignIn.getSignedInAccountFromIntent(data).result.idToken}")
    }

    private fun initGoogleLoginSetting(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
    }

}