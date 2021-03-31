package com.example.vistore.objects

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseObject {


    val firestore: FirebaseFirestore = Firebase.firestore

    const val COLLECTION_USERS = "users"


    fun saveUserInDB(user: User) {
        firestore.collection(COLLECTION_USERS)
            .document(Firebase.auth.currentUser.uid)
            .set(user)
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "SAVED IN DB")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", " FAILURE SAVING IN DB")
            }
    }


    fun retrieveUserFromDB() {
        val uid = Firebase.auth.currentUser.uid
        val remoteUser = firestore.collection(COLLECTION_USERS).document(uid)
            .get()
            .addOnSuccessListener {
                User.receiveRemoteUser(it)
                Log.d("FIRESTOREdb", "RETRIEVED FROM DB")

            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", " FAILURE RETRIEVING FROM DB")
            }
        Log.d("FIRESTOREdb", remoteUser.toString())

    }
}