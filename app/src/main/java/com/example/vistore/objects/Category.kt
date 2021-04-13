package com.example.vistore.objects

data class Category(
    val requestName: String
) {
    override fun toString(): String {
        return requestName.capitalize()
    }
}
