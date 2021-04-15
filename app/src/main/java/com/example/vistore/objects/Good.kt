package com.example.vistore.objects

import com.google.gson.annotations.SerializedName

data class Good(
    @SerializedName("id")
    val goodId: String,
    val brand: String,
    val name: String,
    val price: String,
    val price_sign: String,
    val image_link: String,
    val description: String,
    val category: String,
    val product_type: String,
    val amount_basket: String,
    val value_basket: String
) {
    constructor() : this(
        "", "", "", "", "$", "",
        "", "", "", "", ""
    )
}
