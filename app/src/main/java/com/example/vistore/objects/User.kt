package com.example.vistore.objects

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val country: String,
    val town: String,
    val address: String
){
    constructor() : this("", "", "", "",  "", "",  "")
}
