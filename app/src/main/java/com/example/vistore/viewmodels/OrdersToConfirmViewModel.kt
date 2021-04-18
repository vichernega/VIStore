package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Order
import com.example.vistore.repositories.OrdersToConfirmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersToConfirmViewModel: ViewModel() {

    private val repo: OrdersToConfirmRepository = OrdersToConfirmRepository()

    private var _orderToConfirmListLiveData: MutableLiveData<List<Order>> = repo.orderToConfirmListLiveData

    fun getOrderList(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getOrderList()
        }
    }

    val orderToConfirmListLiveData: MutableLiveData<List<Order>> get() = _orderToConfirmListLiveData
}