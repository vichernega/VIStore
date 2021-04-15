package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vistore.objects.User
import com.example.vistore.objects.UserObject
import com.example.vistore.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel() {

    private val repo: UsersRepository = UsersRepository()
    private var _usersListLiveData: MutableLiveData<List<User>> = repo.usersListLiveData

    fun getUsersList(){
        viewModelScope.launch(Dispatchers.IO) {         // coroutine (suspend function call)
            repo.getUsersList()
        }
    }

    val usersListLiveData: MutableLiveData<List<User>> get() = _usersListLiveData
}