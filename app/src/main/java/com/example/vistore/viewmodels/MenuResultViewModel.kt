package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.Good
import com.example.vistore.repositories.MenuResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuResultViewModel: ViewModel() {

    private val repo = MenuResultRepository()

    private val _categoryResultList = repo.categoryResultList

    fun getProductsByCategory(category: String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProductsByCategory(category)
        }
    }


    val categoryResultList: MutableLiveData<List<Good>> get() = _categoryResultList
}