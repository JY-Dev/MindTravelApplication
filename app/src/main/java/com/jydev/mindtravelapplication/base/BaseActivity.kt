package com.jydev.mindtravelapplication.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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

    open fun activityResultCallback(data : Intent){}

    abstract fun onCreateLifeCycle()
    open fun onDestroyLifeCycle(){}

}