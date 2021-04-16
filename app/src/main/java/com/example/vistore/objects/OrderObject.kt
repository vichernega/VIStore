package com.example.vistore.objects

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

    // status
    var isConfirmed: String = "false"       // true after admin confirming
    var isReceived: String = "false"        // true after user receiving


    fun setUserInfo(
        userName: String,
        userSurname: String,
        userCountry: String,
        userTown: String,
        userAddress: String
    ) {
        this.userName = userName
        this.userSurname = userSurname
        this.userCountry = userCountry
        this.userTown = userTown
        this.userAddress = userAddress
    }

    fun updateOrderGoodInfo(list: List<Good>) {
        goodsList = list
        var count: Double = 0.0
        for (good in list) {
            count += good.value_basket.toDouble()
        }
        totalValue = "%.2f".format(count)   // round total value
    }

    fun generateIds() {
        orderId = UUID.randomUUID().toString()
        userId = Firebase.auth.currentUser.uid
    }

    fun setStatusInfo(){
        isConfirmed = "false"
        isReceived = "false"
    }

    fun chooseDeliveryMan() {
        deliveryMan = "true"
        postOffice = "false"
    }

    fun choosePostOffice() {
        deliveryMan = "false"
        postOffice = "true"
    }

    fun chooseCreditCard() {
        creditCard = "true"
        cash = "false"
    }

    fun chooseCash() {
        creditCard = "false"
        cash = "true"
    }

    fun checkDelivery(): String {
        if (deliveryMan.toBoolean()) {
            return "Delivery man"
        } else {
            return "Post office"
        }
    }

    fun checkPayment(): String {
        if (creditCard.toBoolean()) {
            return "Credit card"
        } else {
            return "Cash"
        }
    }

    fun checkIsConfirmed(): Boolean {         // true if confirmed
/*        Log.d("STATUSfff", isConfirmed)                                           BUG - always return false
        Log.d("STATUSfff", "BOOL before setting:   ${isConfirmed.toBoolean()}")
        isConfirmed = "true"
        Log.d("STATUSfff", "BOOL after setting:   ${isConfirmed.toBoolean()}")*/
        return isConfirmed.toBoolean()
    }

    fun checkIsReceived(): Boolean {         // true if received
/*        Log.d("STATUSfff", isReceived)                                            BUG - always return false
        Log.d("STATUSfff", "BOOL:   ${isReceived.toBoolean()}")*/
        return isReceived.toBoolean()
    }

    fun setOrder(order: Order) {
        orderId = order.orderId
        userId = order.userId
        userName = order.userName
        userSurname = order.userSurname
        userCountry = order.userCountry
        userTown = order.userTown
        userAddress = order.userAddress
        goodsList = order.goodsList
        totalValue = order.totalValue
        deliveryMan = order.deliveryMan
        postOffice = order.postOffice
        creditCard = order.creditCard
        cash = order.cash
        isConfirmed = order.isConfirmed
        isReceived = order.isReceived

/*        Log.d("STATUSfff", "order.isConfirmed:   ${order.isConfirmed}")
        Log.d("STATUSfff", "order.isConfirmed bool:   ${order.isConfirmed.toBoolean()}")
        Log.d("STATUSfff", "order.isReceived:   ${order.isReceived}")
        Log.d("STATUSfff", "order.isReceived bool:   ${order.isReceived.toBoolean()}")*/
    }
}