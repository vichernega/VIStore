package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.UserObject

class ChangeUserInfoRepository {

    private val _boolMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun saveUserData(user: UserObject) {
        _boolMutableLiveData.value = true           // change liveData
        FirebaseObject.saveUserInDB(user)           // save local user in remote one
    }

    val boolMutableLiveData: MutableLiveData<Boolean> get() = _boolMutableLiveData
}