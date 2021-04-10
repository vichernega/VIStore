package com.example.vistore.objects

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

    fun set(good: Good){
        good.goodId?.let { goodId = it }
        good.brand?.let { brand = it }
        good.name?.let { name = it }
        good.price?.let { price = it }
        good.price_sign?.let { price_sign = it }
        good.image_link?.let { image_link = it }
        good.description?.let { description = it }
        good.category?.let { category = it }
        good.product_type?.let { product_type = it }

        /*brand = good.brand
        name = good.name
        price = good.price
        price_sign = good.price_sign
        image_link = good.image_link
        description = good.description
        category = good.category
        product_type = good.product_type*/
    }


}