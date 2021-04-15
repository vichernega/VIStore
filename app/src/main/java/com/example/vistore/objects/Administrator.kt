package com.example.vistore.objects

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

object Administrator {
    var id: String = ""
    var email: String = ""

    fun set(admin: Administrator){
        id = admin.id
        email = admin.email
    }

    fun receiveRemoteAdmin(documentSnapshot: DocumentSnapshot){
        val remoteAdmin = documentSnapshot.toObject<Administrator>()     // convert data from DB to Admin object
        if (remoteAdmin != null) {
            set(remoteAdmin)                                             // set data to Admin Object
        }
    }

    fun clear(){
        id = ""
        email = ""
    }
}