package com.example.vistore.repositories

import com.example.vistore.objects.FirebaseObject

class ConfirmOrderRepository {

    fun saveOrder(userId: String){
        FirebaseObject.saveOrderInDB(userId)      // update order in db
    }
}