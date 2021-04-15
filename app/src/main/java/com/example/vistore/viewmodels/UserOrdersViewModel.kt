package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Order
import com.example.vistore.repositories.UserOrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserOrdersViewModel: ViewModel() {

    private val repo: UserOrdersRepository = UserOrdersRepository()

    private var _orderListLiveData: MutableLiveData<List<Order>> = repo.orderListLiveData

    fun getOrderList(uid: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getOrderList(uid)
        }
    }

    val orderListLiveData: MutableLiveData<List<Order>> get() = _orderListLiveData
}