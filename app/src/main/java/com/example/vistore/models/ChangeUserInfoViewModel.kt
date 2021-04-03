package com.example.vistore.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vistore.objects.User
import com.example.vistore.repositories.ChangeUserInfoRepository

class ChangeUserInfoViewModel: ViewModel() {

    private val repo = ChangeUserInfoRepository()
    private val _boolMutableLiveData = repo.boolMutableLiveData

    fun saveUserData(name: String, surname: String, country: String, town: String, address: String){
        User.saveData(name, surname, country, town, address)
        repo.saveUserData(User)
    }

    val boolMutableLiveData: MutableLiveData<Boolean> get() = _boolMutableLiveData
}