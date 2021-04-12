package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.GoodObject
import com.example.vistore.utilits.showToast

class GoodRepository {

    private var _isGoodInBasketLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isGoodInBasketLiveData: MutableLiveData<Boolean> get() = _isGoodInBasketLiveData

    suspend fun checkIsGoodAlreadyInBasket(){
        _isGoodInBasketLiveData.postValue(FirebaseObject.checkIsGoodAlreadyInBasket())
        //Log.d("FIRESTOREdb", "REPO:   isGoodInBasket = ${_isGoodInBasketLiveData.value.toString()}")
    }

    suspend fun addToBasket(){
        GoodObject.addToBasket()
        FirebaseObject.saveGoodInUsersBasket(GoodObject)
        checkIsGoodAlreadyInBasket()
    }

    suspend fun deleteFromBasket(){
        GoodObject.deleteFromBasketTotally()
        FirebaseObject.deleteGoodFromUsersBasket(GoodObject)
        checkIsGoodAlreadyInBasket()
    }
}