package com.example.vistore.objects

object GoodObject {

    var goodId: String = ""
    var brand: String = ""
    var name: String = ""
    var price: String = ""
    var price_sign: String = ""
    var image_link: String = ""
    var description: String = ""
    var category: String = ""
    var product_type: String = ""

    fun set(good: Good){
        goodId = good.goodId
        brand = good.brand
        name = good.name
        price = good.price
        price_sign = good.price_sign
        image_link = good.image_link
        description = good.description
        category = good.category
        product_type = good.product_type
    }


}