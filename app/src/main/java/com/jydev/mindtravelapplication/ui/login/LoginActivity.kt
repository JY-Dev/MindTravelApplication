package com.jydev.mindtravelapplication.ui.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.jydev.mindtravelapplication.BuildConfig
import com.jydev.mindtravelapplication.ui.main.MainActivity
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.data.model.login.SocialLoginRequest
import com.jydev.mindtravelapplication.data.model.login.SocialLoginType
import com.jydev.mindtravelapplication.databinding.ActivityLoginBinding
import com.jydev.mindtravelapplication.ui.editnickname.EditNicknameActivity
import com.jydev.mindtravelapplication.ui.login.LoginViewModel.*
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
        loginViewModel.observeError()
        loginViewModel.moveScreen.observe(this){
            val intent : Intent = when(it){
                is MoveScreen.MainScreen -> {
                    Intent(this@LoginActivity, MainActivity::class.java)
                }
                is MoveScreen.EditNicknameScreen -> {
                    Intent(this@LoginActivity,EditNicknameActivity::class.java).apply {
                        putExtra(EditNicknameActivity.WHERE_COME,"LoginActivity")
                    }
                }
            }
            startActivity(intent)
            finish()
        }
    }

    override fun activityResultCallback(data : Intent){
        val accessToken = GoogleSignIn.getSignedInAccountFromIntent(data).result.idToken?:""
        issuedFcmToken {
            loginViewModel.socialLogin(SocialLoginRequest(SocialLoginType.GOOGLE,accessToken, it))
        }
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
                issuedFcmToken {
                    loginViewModel.socialLogin(SocialLoginRequest(SocialLoginType.NAVER,NaverIdLoginSDK.getAccessToken()!!,it))
                }
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

    private fun issuedFcmToken(block :(fcmToken : String) -> Unit){
        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if(it.isComplete){
                block(it.result)
            } else {
                Toast.makeText(this,"로그인에 실패 했습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }

}