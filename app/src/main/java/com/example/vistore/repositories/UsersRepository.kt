package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.User
import com.example.vistore.objects.UserObject

class UsersRepository {

    private var _usersListLiveData: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun getUsersList(){
        _usersListLiveData.postValue(FirebaseObject.retrieveListOfUsers())      //set list of users to live data
    }

    val usersListLiveData: MutableLiveData<List<User>> get() = _usersListLiveData
}