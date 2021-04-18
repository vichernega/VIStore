package com.example.vistore.viewmodels

import androidx.lifecycle.ViewModel
import com.example.vistore.objects.OrderObject
import com.example.vistore.repositories.ReceiveOrderRepository

class ReceiveOrderViewModel: ViewModel() {

    private val repo = ReceiveOrderRepository()

    fun receive(){
        OrderObject.received = true.toString()
        repo.saveOrder(OrderObject.userId)
    }

    fun savePdf(){

        repo.savePdf()
    }
}