package com.example.vistore.api

import com.example.vistore.utilits.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBuilder {

    // increase query response time
    private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()

    private val retrofit = Retrofit.Builder()
        // converter factory builds a web services API. Converts json to string
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(client)
        .build()

    // connecting retrofit object and interface GoodsApiService
    val retrofitService: GoodsApiService by lazy { retrofit.create(GoodsApiService::class.java) }
}