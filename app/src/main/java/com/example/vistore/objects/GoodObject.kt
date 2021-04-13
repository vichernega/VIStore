package com.example.vistore.objects

import com.example.vistore.utilits.showToast

object GoodObject {

    var goodId: String = ""
    var brand: String = ""
    var name: String = ""
    var price: String = ""
    var price_sign: String = "$"
    var image_link: String = ""
    var description: String = ""
    var category: String = "NO NAME"
    var product_type: String = ""
    var amount_basket: String = "0"
    var value_basket: String = ""

    fun set(good: Good){
        good.goodId.let { goodId = it }
        good.brand.let { brand = it }
        good.name.let { name = it }
        good.price.let { price = it }
        good.price_sign.let { price_sign = it }
        good.image_link.let { image_link = it }
        good.description.let { description = it }
        good.category.let { category = it }
        good.product_type.let { product_type = it }
    }

    fun addToBasket(){
        amount_basket = "1"
        value_basket = price
    }

    fun addOneMoreInBasket(){
        amount_basket = (amount_basket.toInt() + 1).toString()
        value_basket = "%.2f".format(amount_basket.toInt() * price.toDouble())
    }

    fun deleteOneFromBasket(){
        if (amount_basket.toInt() > 1){
            amount_basket = (amount_basket.toInt() - 1).toString()
            value_basket = (amount_basket.toInt() * price.toDouble()).toString()
        } else showToast("Can't order less than 1 item!")
    }

    fun deleteFromBasketTotally(){
        amount_basket = "0"
        value_basket = ""
    }


}