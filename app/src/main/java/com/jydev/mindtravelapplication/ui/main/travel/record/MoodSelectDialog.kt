package com.jydev.mindtravelapplication.ui.main.travel.record

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jydev.mindtravelapplication.databinding.DialogSelectFeelingBinding
import com.jydev.mindtravelapplication.domain.model.Mood

class MoodSelectDialog : DialogFragment() {
    private var _binding : DialogSelectFeelingBinding? = null
    private val binding :DialogSelectFeelingBinding
        get() = _binding!!

    private lateinit var listener : FeelingSelect

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSelectFeelingBinding.inflate(inflater,container,false)
        binding.initView()
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    private fun DialogSelectFeelingBinding.initView(){
        selectButton.setOnClickListener {
            val index = when(feelingRadioGroup.checkedRadioButtonId){
                badRadioButton.id -> 0
                aLittleBadRadioButton.id -> 1
                normalRadioButton.id -> 2
                else -> 3
            }
            listener.selectFeeling(Mood.getFeeling(index))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as FeelingSelect
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface FeelingSelect{
        fun selectFeeling(mood: Mood)
    }
}