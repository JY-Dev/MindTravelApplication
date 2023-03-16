package com.jydev.mindtravelapplication.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.viewbinding.ViewBinding
import com.jydev.mindtravelapplication.ui.login.LoginActivity


abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) : AppCompatActivity() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let(::activityResultCallback)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        onCreateLifeCycle()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyLifeCycle()
        _binding = null
    }

    fun NetworkViewModel.observeError(){
        tokenExpired.observe(this@BaseActivity){
            it.getContentIfNotHandled()?.let {
                val intent = Intent(this@BaseActivity,LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                Toast.makeText(this@BaseActivity,"로그인 정보가 만료되었습니다.",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }
        errorMessage.observe(this@BaseActivity){
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this@BaseActivity,message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    open fun activityResultCallback(data : Intent){}

    abstract fun onCreateLifeCycle()
    open fun onDestroyLifeCycle(){}

}