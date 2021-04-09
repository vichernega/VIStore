package com.example.vistore.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vistore.api.ApiBuilder
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeRepository {

    private val _responseListLivaData: MutableLiveData<List<Good>> = MutableLiveData()

    val responseListLiveData: MutableLiveData<List<Good>> get() = _responseListLivaData

    suspend fun getProducts() {

        // values for api key and host
        val headers = HashMap<String, String>()
        headers.put("x-rapidapi-key", "3e7abad104mshab610049e8e18d7p16ff5fjsn5e4dbb01f073")
        headers.put("x-rapidapi-host", "makeup.p.rapidapi.com")

        // response - retrofit object that contains List<Good>
        try {

            val response = ApiBuilder.retrofitService.getProducts("lipstick", headers)
            _responseListLivaData.value = response

            Log.d("RESPONSE", response.toString())
            Log.d("RESPONSE", response.size.toString())

        } catch (e: Exception){
            Log.d("RESPONSE", e.message.toString())
        }
    }
}


/*
for not suspend fun that returns Call<List<Good>>
val response = call.enqueue(object : Callback<List<Good>> {

    // returns Good objects
    override fun onResponse(call: Call<List<Good>>, response: Response<List<Good>>) {

        Log.d("RESPONSE", response.body().toString())
    }

    override fun onFailure(call: Call<List<Good>>, t: Throwable) {
        Log.d("RESPONSE", t.message)
    }

})*/