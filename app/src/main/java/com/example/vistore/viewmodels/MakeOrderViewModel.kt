package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.OrderObject
import com.example.vistore.repositories.MakeOrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MakeOrderViewModel: ViewModel() {
    private val repo = MakeOrderRepository()
    private var _orderTotalValueLiveData = repo.orderTotalValueLiveData

    fun countTotalValue(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.countTotalValue()
        }
    }

    fun saveOrder(userName: String, userSurname: String, userCountry: String, userTown: String, userAddress: String){
        OrderObject.setUserInfo(userName, userSurname, userCountry, userTown, userAddress)
        OrderObject.setStatusInfo()     // confirmed, received - false
        repo.saveOrder()
    }

    val orderTotalValueLiveData: MutableLiveData<String> get() = _orderTotalValueLiveData
}