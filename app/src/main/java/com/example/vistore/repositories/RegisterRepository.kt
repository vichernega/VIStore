package com.example.vistore.repositories

import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.UserObject
import com.example.vistore.utilits.showToast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterRepository {

    private val _userMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    val userMutableLiveData: MutableLiveData<FirebaseUser> get() = _userMutableLiveData


    fun signUp(email:String, password: String){

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast("Success")

                    _userMutableLiveData.value = Firebase.auth.currentUser      //change liveData
                    FirebaseObject.saveUserInDB(UserObject)                           // Save user data in firestore

                } else {
                    showToast("The user is already exists")
                }
            }

    }

    fun login(email: String, password: String){

        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast("Success")

                    _userMutableLiveData.value = Firebase.auth.currentUser      // change liveData

                    GlobalScope.launch(Dispatchers.IO) {                        //coroutine because retrieveUserFromDB is suspend function
                        FirebaseObject.retrieveUserFromDB()                     // retrieve remote data to local user
                    }

                } else {
                    showToast("Incorrect data")
                }
        }
    }
}
