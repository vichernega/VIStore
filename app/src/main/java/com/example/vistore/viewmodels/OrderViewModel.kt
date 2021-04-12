package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.Disposable
import com.example.vistore.objects.OrderObject
import com.example.vistore.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel: ViewModel() {
    private val repo = OrderRepository()
    private var _orderTotalValueLiveData = repo.orderTotalValueLiveData

    fun countTotalValue(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.countTotalValue()
        }
    }

    fun saveOrder(userName: String, userSurname: String, userCountry: String, userTown: String, userAddress: String){
        OrderObject.setUserInfo(userName, userSurname, userCountry, userTown, userAddress)
        repo.saveOrder()
    }

    val orderTotalValueLiveData: MutableLiveData<String> get() = _orderTotalValueLiveData
}