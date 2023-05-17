package com.jydev.mindtravelapplication.ui.main.consulting.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jydev.mindtravelapplication.base.NetworkViewModel
import com.jydev.mindtravelapplication.data.repository.StoreRepository
import com.jydev.mindtravelapplication.domain.model.StoreItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository) : NetworkViewModel() {
    private val _storeItems = MutableLiveData<List<StoreItem>>()
    val storeItems : LiveData<List<StoreItem>>
        get() = _storeItems

    fun purchaseItem(item : StoreItem){

    }

    fun fetchStoreItems(){
        getApiResult({
            storeRepository.fetchStoreItems()
        }){
            _storeItems.value = it
        }
    }
}