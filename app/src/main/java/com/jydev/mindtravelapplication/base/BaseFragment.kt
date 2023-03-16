package com.jydev.mindtravelapplication.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jydev.mindtravelapplication.ui.login.LoginActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
): Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreateLifeCycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyViewLifeCycle()
        _binding = null
    }

    fun NetworkViewModel.observeErrorHandling(){
        tokenExpired.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let {
                activity?.let {
                    val intent = Intent(it, LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    Toast.makeText(it,"로그인 정보가 만료되었습니다.",Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }

            }
        }
        errorMessage.observe(this@BaseFragment){
            it.getContentIfNotHandled()?.let { message ->
                context?.let {
                    Toast.makeText(it,message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    abstract fun onViewCreateLifeCycle()
    open fun onDestroyViewLifeCycle(){}
}