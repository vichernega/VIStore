package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Order
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentUserOrdersRepository {

    private var _orderList: MutableLiveData<List<Order>> = MutableLiveData()

    suspend fun getOrderList(){
        _orderList.postValue(FirebaseObject.retrieveUserOrdersList(Firebase.auth.currentUser.uid))
    }

    val orderList: MutableLiveData<List<Order>> get() = _orderList
}