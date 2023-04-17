package com.jydev.mindtravelapplication.ui.start

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.jydev.mindtravelapplication.ui.main.MainActivity
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityStartBinding
import com.jydev.mindtravelapplication.ui.editnickname.EditNicknameActivity
import com.jydev.mindtravelapplication.ui.login.LoginActivity
import com.jydev.mindtravelapplication.ui.start.StartViewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>(ActivityStartBinding::inflate), PermissionListener {
    private val startViewModel by viewModels<StartViewModel>()
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    private fun observeData(){
        startViewModel.observeError()
        startViewModel.moveScreen.observe(this){
            val intent : Intent = when(it){
                is MoveScreen.MainScreen -> {
                    Intent(this@StartActivity, MainActivity::class.java)
                }
                is MoveScreen.LoginScreen -> {
                    Intent(this@StartActivity,LoginActivity::class.java)
                }
                is MoveScreen.EditNicknameScreen -> {
                    Intent(this@StartActivity,EditNicknameActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }
    }

    private fun askNotificationPermission() {
        val arr = mutableListOf<String>().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        TedPermission.create()
            .setPermissionListener(this)
            .setDeniedMessage("원활한 서비스 사용을 위해 권한 설정을 해주세요!")
            .setPermissions(*arr.toTypedArray())
            .check()
    }

    private fun ActivityStartBinding.initView(){
        startButton.setOnClickListener {
            askNotificationPermission()
        }
    }

    override fun onPermissionGranted() {
        startViewModel.moveScreen()
    }

    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

    }
}