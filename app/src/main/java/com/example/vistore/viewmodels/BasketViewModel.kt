package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Good
import com.example.vistore.objects.GoodObject
import com.example.vistore.repositories.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel : ViewModel() {

    private val repo: BasketRepository = BasketRepository()
    private var _basketGoodsList = repo.basketGoodsList

    fun retrieveGoodsListFromBasket() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.retrieveGoodsListFromBasket()
        }
    }

    val basketGoodsList: MutableLiveData<List<Good>> get() = _basketGoodsList
}