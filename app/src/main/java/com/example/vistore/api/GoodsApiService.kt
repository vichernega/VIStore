package com.example.vistore.api

import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface GoodsApiService {

    @GET("/products.json")
    suspend fun getProductsByCategory(
        @Query("product_category")
        productCategory: String,
        @HeaderMap headers: Map<String, String>

    ): List<Good>

    @GET("/products.json")
    suspend fun getProductsHome(
        @HeaderMap headers: Map<String, String>,
        @Query("rating_greater_than")
        ratingGreaterThan: String = "4.9",        // returns nullable objects in some cases ( api problem)
        @Query("price_greater_than")
        priceGreaterThan: String = "12"

    ): List<Good>
}