package com.jydev.mindtravelapplication.ui.main.travel

import android.content.Intent
import androidx.fragment.app.viewModels
import com.jydev.mindtravelapplication.base.BaseFragment
import com.jydev.mindtravelapplication.databinding.FragmentTravelBinding
import com.jydev.mindtravelapplication.ui.main.travel.record.RecordActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class TravelFragment : BaseFragment<FragmentTravelBinding>(FragmentTravelBinding::inflate) {
    private val travelViewModel by viewModels<TravelViewModel>()
    override fun onViewCreateLifeCycle() {
        binding.initView()
        travelViewModel.getMember()
        observeData()
    }

    private fun observeData(){
        travelViewModel.member.observe(viewLifecycleOwner){
            binding.nicknameTextView.text = "${it.nickname}님"
            calculateTravelDay(it.createdDate)
        }
    }

    private fun calculateTravelDay(dateTime : String){
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.KOREA)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val createTime = dateFormat.parse(dateFormat.format(format.parse(dateTime)!!.time))!!.time
        val currentTime = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))!!.time
        val startTimeMills = currentTime - createTime
        val startDay = startTimeMills/1000/3600/24+1
        binding.travelDayTextView.text = "마음 여행한지 ${startDay}일"
    }

    private fun FragmentTravelBinding.initView(){
        recordButton.setOnClickListener {
            startActivity(Intent(context,RecordActivity::class.java))
        }
    }
}