package com.example.vistore.objects

import android.util.Log
import com.example.vistore.utilits.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

object FirebaseObject {


    val firestore: FirebaseFirestore = Firebase.firestore

    const val COLLECTION_USERS = "users"
    const val COLLECTION_USERS_BASKET = "users basket"


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

    fun saveGoodInUsersBasket(good: GoodObject){
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(good.goodId)
            .set(good)
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "GOOD IS SUCCESSFULLY SAVED IN USERS_BASKET")
                showToast("Saved successfully!")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT SAVED IN USERS_BASKET")
                showToast("Failure")
            }
    }

    fun deleteGoodFromUsersBasket(good: GoodObject){
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(good.goodId)
            .delete()
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "GOOD SUCCESSFULLY DELETED FROM USERS_BASKET")
                showToast("Deleted successfully")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT DELETED FROM USERS_BASKET")
                showToast("Failure")
            }
    }


    suspend fun checkIsGoodAlreadyInBasket(): Boolean{
        var isGoodInBasket = false
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(GoodObject.goodId)
            .get()
            .addOnSuccessListener {
                if(it.exists()) {
                    isGoodInBasket = true
                    Log.d("FIRESTOREdb", "GOOD IS ALREADY EXISTS IN USERS_BASKET")
                }
                else{
                    Log.d("FIRESTOREdb", "GOOD DOESN'T IN USERS_BASKET")
                }
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE IN IS GOOD EXISTS FUN")
            }
        delay(600)
        return isGoodInBasket  // true if good is in DB
    }
}