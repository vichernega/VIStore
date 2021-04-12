package com.example.vistore.objects

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.internal.addHeaderLenient
import java.util.*

object OrderObject {

    var orderId: String = ""

    // user info
    var userId = ""
    var userName = ""
    var userSurname = ""
    var userCountry = ""
    var userTown = ""
    var userAddress = ""

    // list of products
    var goodsList: List<Good> = listOf()
    var totalValue = ""

    // delivery information
    var deliveryMan: String = "false"      // boolean
    var postOffice: String = "false"

    // payment
    var creditCard: String = "false"
    var cash: String = "false"


    fun setUserInfo(userName: String, userSurname: String, userCountry: String, userTown: String, userAddress: String){
        this.userName = userName
        this.userSurname = userSurname
        this.userCountry = userCountry
        this.userTown = userTown
        this.userAddress = userAddress
    }

    fun updateOrderGoodInfo(list: List<Good>){
        goodsList = list
        var count: Double = 0.0
        for (good in list){
            count += good.value_basket.toDouble()
        }
        totalValue = "%.2f".format(count)   // round total value
    }

    fun generateIds(){
        orderId = UUID.randomUUID().toString()
        userId = Firebase.auth.currentUser.uid
    }

    fun chooseDeliveryMan(){
        deliveryMan = "true"
        postOffice = "false"
    }

    fun choosePostOffice(){
        deliveryMan = "false"
        postOffice = "true"
    }

    fun chooseCreditCard(){
        creditCard = "true"
        cash = "false"
    }

    fun chooseCash(){
        creditCard = "false"
        cash = "true"
    }
}