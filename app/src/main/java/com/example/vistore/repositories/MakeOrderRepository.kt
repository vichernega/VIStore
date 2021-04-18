package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Good
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MakeOrderRepository {

    private var _orderTotalValueLiveData: MutableLiveData<String> = MutableLiveData()

    suspend fun countTotalValue(){
        val orderList: List<Good> = FirebaseObject.retrieveGoodListFromBasket()
        OrderObject.updateOrderGoodInfo(orderList)
        _orderTotalValueLiveData.postValue(OrderObject.totalValue)

    }

    fun saveOrder(){
        OrderObject.generateIds()
        FirebaseObject.saveOrderInDB(Firebase.auth.currentUser.uid)
        showToast("Order confirmed")
    }

    val orderTotalValueLiveData: MutableLiveData<String> get() = _orderTotalValueLiveData

}