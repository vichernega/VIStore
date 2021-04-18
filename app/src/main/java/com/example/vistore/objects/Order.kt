package com.example.vistore.objects

data class Order(
    val orderId: String,

    // user info
    val userId: String,
    val userName: String,
    val userSurname: String,
    val userCountry: String,
    val userTown: String,
    val userAddress: String,

    // list of products
    val goodsList: List<Good>,
    val totalValue: String,

    // delivery information
    val deliveryMan: String,      // boolean
    val postOffice: String,

    // payment
    val creditCard: String,
    val cash: String,

    // status
    val confirmed: String,       // true after admin confirming
    val received: String         // true after user receiving
){
    constructor() : this("", "", "", "", "", "", "",
        listOf(), "",  "", "", "", "", "", "", )
}
