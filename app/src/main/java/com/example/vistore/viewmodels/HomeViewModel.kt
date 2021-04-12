package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Good
import com.example.vistore.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val repo = HomeRepository()
    private val _responseListLivaData: MutableLiveData<List<Good>> = repo.responseListLiveData

    fun getProducts(){
        viewModelScope.launch {
            repo.getProducts()
        }
    }

    val responseListLiveData: MutableLiveData<List<Good>> get() = _responseListLivaData


}