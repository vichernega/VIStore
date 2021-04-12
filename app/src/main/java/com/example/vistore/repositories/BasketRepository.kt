package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodObject

class BasketRepository {

    private var _basketGoodsList: MutableLiveData<List<Good>> = MutableLiveData()
    val basketGoodsList: MutableLiveData<List<Good>> get() = _basketGoodsList

    suspend fun retrieveGoodsListFromBasket(){
        _basketGoodsList.postValue(FirebaseObject.retrieveGoodListFromBasket())
    }
}