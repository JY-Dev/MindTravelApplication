package com.jydev.mindtravelapplication.ui.main.consulting

import android.content.Intent
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.databinding.FragmentConsultingBinding
import com.jydev.mindtravelapplication.ui.main.consulting.store.StoreActivity


class ConsultingFragment : BaseFragment<FragmentConsultingBinding>(FragmentConsultingBinding::inflate) {

    override fun onViewCreateLifeCycle() {
        binding.initView()
    }

    private fun FragmentConsultingBinding.initView(){
        ticketImage.setOnClickListener{
            gotoStore()
        }
        ticketCountTextView.setOnClickListener {
            gotoStore()
        }
    }

    private fun gotoStore(){
        context?.let {
            startActivity(Intent(it,StoreActivity::class.java))
        }
    }

}