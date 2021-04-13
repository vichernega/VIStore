package com.example.vistore.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vistore.api.ApiBuilder
import com.example.vistore.objects.Good
import com.example.vistore.utilits.HEADERS

class HomeRepository {

    private val _responseListLivaData: MutableLiveData<List<Good>> = MutableLiveData()

    val responseListLiveData: MutableLiveData<List<Good>> get() = _responseListLivaData

    suspend fun getProducts() {

        // response - retrofit object that contains List<Good>
        try {

            val response = ApiBuilder.retrofitService.getProductsHome(HEADERS)
            _responseListLivaData.postValue(response)

            Log.d("RESPONSE", response.toString())
            Log.d("RESPONSE", response.size.toString())

        } catch (e: Exception){
            Log.d("RESPONSE", e.message.toString())
        }
    }

}





/*
// for not suspend fun that returns Call<List<Good>>

val response = call.enqueue(object : Callback<List<Good>> {

    // returns Good objects
    override fun onResponse(call: Call<List<Good>>, response: Response<List<Good>>) {

        Log.d("RESPONSE", response.body().toString())
    }

    override fun onFailure(call: Call<List<Good>>, t: Throwable) {
        Log.d("RESPONSE", t.message)
    }

})*/