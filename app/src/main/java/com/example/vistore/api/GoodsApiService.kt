package com.example.vistore.api

import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface GoodsApiService {

    @GET("/products.json")
    suspend fun getProducts(
        @Query("product_category")
        productCategory: String,
        @HeaderMap headers: Map<String, String>

    ): List<Good>
}