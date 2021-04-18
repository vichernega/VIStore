package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Order
import com.example.vistore.repositories.CurrentUserOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentUserOrdersViewModel: ViewModel() {

    private val repo = CurrentUserOrdersRepository()
    private var _orderList = repo.orderList

    fun getOrderList(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getOrderList()
        }
    }

    val orderList: MutableLiveData<List<Order>> get() = _orderList
}