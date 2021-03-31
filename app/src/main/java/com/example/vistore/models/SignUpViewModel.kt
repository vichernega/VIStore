package com.example.vistore.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vistore.repositories.RegisterRepository
import com.google.firebase.auth.FirebaseUser

class SignUpViewModel: ViewModel() {

    private val repo = RegisterRepository()
    private val _userMutableLiveData = repo.userMutableLiveData


    fun trySignUp(email: String, password: String) {
        repo.signUp(email, password)
    }

    val userMutableLiveData: MutableLiveData<FirebaseUser> get() = _userMutableLiveData
}
