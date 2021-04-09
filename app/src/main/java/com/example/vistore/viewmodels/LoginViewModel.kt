package com.example.vistore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vistore.repositories.RegisterRepository
import com.google.firebase.auth.FirebaseUser

class LoginViewModel: ViewModel() {

    private val repo = RegisterRepository()
    private val _userMutableLiveData = repo.userMutableLiveData

    fun login(email: String, password: String){
        repo.login(email, password)
    }

    val userMutableLiveData: MutableLiveData<FirebaseUser> get() = _userMutableLiveData
}