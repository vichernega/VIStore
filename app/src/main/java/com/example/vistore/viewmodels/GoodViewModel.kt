package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.repositories.GoodRepository
import com.example.vistore.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoodViewModel: ViewModel() {

    private val repo = GoodRepository()
    private var _isGoodInBasketLiveData: MutableLiveData<Boolean> = repo.isGoodInBasketLiveData
    val isGoodInBasketLiveData: MutableLiveData<Boolean> get() = _isGoodInBasketLiveData

    fun checkIsGoodAlreadyInBasket(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.checkIsGoodAlreadyInBasket()
           //Log.d("FIRESTOREdb", "VIEW MODEL:   isGoodInBasket = ${_isGoodInBasketLiveData.value.toString()}")
        }
    }

    fun addToBasket(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addToBasket()
        }
    }

    fun deleteFromBasket(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFromBasket()
        }
    }

}