package com.example.vistore.viewmodels

import androidx.lifecycle.ViewModel
import com.example.vistore.objects.OrderObject
import com.example.vistore.repositories.ConfirmOrderRepository

class ConfirmOrderViewModel: ViewModel() {

    private val repo = ConfirmOrderRepository()

    fun confirm(){
        OrderObject.confirmed = true.toString()     // change "confirmed" to true
        repo.saveOrder(OrderObject.userId)
    }

}