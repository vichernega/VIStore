package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Order

class UserOrdersRepository {

    private var _orderListLiveData: MutableLiveData<List<Order>> = MutableLiveData()

    suspend fun getOrderList(uid: String){
        _orderListLiveData.postValue(FirebaseObject.retrieveUserOrdersList(uid))
    }

    val orderListLiveData: MutableLiveData<List<Order>> get() = _orderListLiveData
}