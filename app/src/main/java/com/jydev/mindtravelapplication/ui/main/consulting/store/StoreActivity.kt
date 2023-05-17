package com.jydev.mindtravelapplication.ui.main.consulting.store

import androidx.activity.viewModels
import com.jydev.mindtravelapplication.base.BaseActivity
import com.jydev.mindtravelapplication.databinding.ActivityStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreActivity : BaseActivity<ActivityStoreBinding>(ActivityStoreBinding::inflate) {
    private val viewModel by viewModels<StoreViewModel>()
    private val adapter by lazy {
        StoreItemAdapter(viewModel::purchaseItem)
    }
    override fun onCreateLifeCycle() {
        binding.initView()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchStoreItems()
    }

    private fun observeData(){
        viewModel.observeError()
        viewModel.storeItems.observe(this,adapter::setData)
    }

    private fun ActivityStoreBinding.initView(){
        itemRecyclerView.adapter = adapter
        backButton.setOnClickListener {
            finish()
        }
    }

}