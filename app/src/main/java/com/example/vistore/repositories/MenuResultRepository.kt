package com.example.vistore.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vistore.api.ApiBuilder
import com.example.vistore.objects.Good
import com.example.vistore.utilits.HEADERS

class MenuResultRepository {

    private var _categoryResultList: MutableLiveData<List<Good>> = MutableLiveData()

    suspend fun getProductsByCategory(category: String) {

        try {
            val response = ApiBuilder.retrofitService.getProductsByCategory(category, HEADERS)  // get category data
            _categoryResultList.postValue(response)         // set retrieved data to liveData
            Log.d("RESPONSE", response.toString())

        } catch (e: Exception) {
            Log.d("RESPONSE", e.message.toString())
        }
    }

    val categoryResultList: MutableLiveData<List<Good>> get() = _categoryResultList
}