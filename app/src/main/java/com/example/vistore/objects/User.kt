package com.example.vistore.objects

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object User {

    var id: String = ""
    var name: String = ""
    var surname: String = ""
    var email: String = ""
    var country: String = ""
    var town: String = ""
    var address: String = ""


    fun saveSignUpData(name: String, surname: String, email: String){
        id = Firebase.auth.currentUser.uid
        this.name = name
        this.surname = surname
        this.email = email
    }

    // converting and setting remote user data in local object
    fun receiveRemoteUser(documentShapshot: DocumentSnapshot) {
        val remoteUser = documentShapshot.toObject<User>()
        if (remoteUser != null) {
            setUser(remoteUser)
        }
    }

    // for retrieving user data on app launching
    fun checkUser(){
        if (Firebase.auth.currentUser.uid != null){
            FirebaseObject.retrieveUserFromDB()
        }
    }

    fun setUser(user: User){
        id = user.id
        name = user.name
        surname = user.surname
        email = user.email
        country = user.country
        town = user.town
        address = user.address
    }

    fun clear(){
        id = ""
        name = ""
        surname = ""
        email = ""
        country = ""
        town = ""
        address = ""
    }

}