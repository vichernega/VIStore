package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vistore.objects.UserObject
import com.example.vistore.repositories.ChangeUserInfoRepository

class ChangeUserInfoViewModel: ViewModel() {

    private val repo = ChangeUserInfoRepository()
    private val _boolMutableLiveData = repo.boolMutableLiveData

    fun saveUserData(name: String, surname: String, country: String, town: String, address: String){
        UserObject.saveData(name, surname, country, town, address)
        repo.saveUserData(UserObject)
    }

    val boolMutableLiveData: MutableLiveData<Boolean> get() = _boolMutableLiveData
}