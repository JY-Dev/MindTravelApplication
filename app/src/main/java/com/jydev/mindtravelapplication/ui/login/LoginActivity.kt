package com.jydev.mindtravelapplication.ui.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.jydev.mindtravelapplication.BuildConfig
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.login.SocialLoginRequest
import com.jydev.mindtravelapplication.data.model.login.SocialLoginType
import com.jydev.mindtravelapplication.databinding.ActivityLoginBinding
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var googleSignInClient : GoogleSignInClient
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreateLifeCycle() {
        NaverIdLoginSDK.initialize(this,BuildConfig.NAVER_CLIENT_ID,BuildConfig.NAVER_CLIENT_SECRET,"AAA")
        initGoogleLoginSetting()
        binding.initView()
        observeData()
    }

    private fun ActivityLoginBinding.initView(){
        googleLoginButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startForResult.launch(signInIntent)
        }
        naverLoginButton.setOnClickListener {
            naverLogin()
        }
    }

    private fun observeData(){
        loginViewModel.member.observe(this){

        }
    }

    override fun activityResultCallback(data : Intent){
        val accessToken = GoogleSignIn.getSignedInAccountFromIntent(data).result.idToken?:""
        loginViewModel.socialLogin(SocialLoginRequest(SocialLoginType.GOOGLE,accessToken))
        Log.d("Login","로그인 입니다!!! ${GoogleSignIn.getSignedInAccountFromIntent(data).result.idToken}")
    }

    private fun initGoogleLoginSetting(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    private fun naverLogin(){
        /**
         * OAuthLoginCallback을 authenticate() 메서드 호출 시 파라미터로 전달하거나 NidOAuthLoginButton 객체에 등록하면 인증이 종료되는 것을 확인할 수 있습니다.
         */
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                loginViewModel.socialLogin(SocialLoginRequest(SocialLoginType.NAVER,NaverIdLoginSDK.getAccessToken()!!))
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Toast.makeText(applicationContext,"errorCode:$errorCode, errorDesc:$errorDescription",Toast.LENGTH_SHORT).show()
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }

}