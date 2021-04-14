package com.example.vistore.objects

import android.util.Log
import com.example.vistore.utilits.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object FirebaseObject {


    val firestore: FirebaseFirestore = Firebase.firestore

    private const val COLLECTION_USERS = "users"
    private const val COLLECTION_ADMINS = "administrators"
    private const val COLLECTION_USERS_BASKET = "users basket"
    private const val COLLECTION_USERS_ORDERS = "users_orders"


    fun saveUserInDB(user: User) {
        firestore.collection(COLLECTION_USERS)
            .document(Firebase.auth.currentUser.uid)                // creating document called User.uid in Firestore
            .set(user)                                              // saving user info in document
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "SAVED IN DB")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", " FAILURE SAVING IN DB")
            }
    }


    suspend fun retrieveUserFromDB() {
        val uid = Firebase.auth.currentUser.uid

        // retrieve ADMIN info in local object
        if (isCurrentUserAdmin()){
            firestore.collection(COLLECTION_ADMINS).document(uid)
                .get()                                              // get admin from DB Administrators collection
                .addOnSuccessListener {
                    Administrator.receiveRemoteAdmin(it)            // saving data to local admin object
                    Log.d("FIRESTOREdb", "RETRIEVED FROM DB")
                }
                .addOnFailureListener { Log.d("FIRESTOREdb", " FAILURE RETRIEVING ADMIN FROM DB") }
        }

        // retrieve USER info in local object
        else {
           firestore.collection(COLLECTION_USERS).document(uid)
                .get()                                              // get user from DB USERS collection
                .addOnSuccessListener {
                    User.receiveRemoteUser(it)                      // saving data to local user object
                    Log.d("FIRESTOREdb", "RETRIEVED FROM DB")
                }
                .addOnFailureListener { Log.d("FIRESTOREdb", " FAILURE RETRIEVING USER FROM DB") }
        }

    }

    fun saveGoodInUsersBasket(good: GoodObject){
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(good.goodId)
            .set(good)
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "GOOD IS SUCCESSFULLY SAVED IN USERS_BASKET")
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
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT DELETED FROM USERS_BASKET")
                showToast("Failure")
            }
    }

    fun clearUsersBasket(){

        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)
            .get()
            .addOnSuccessListener {
                for (document in it){
                    firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
                        .collection(COLLECTION_USERS_BASKET).document(document.id)
                        .delete()
                }
                Log.d("FIRESTOREdb", "GOOD SUCCESSFULLY DELETED FROM USERS_BASKET")
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
            .await()            // wait for query finished
        return isGoodInBasket  // true if good is in DB
    }

    suspend fun checkIsBasketEmpty(): Boolean{
        var isBasketEmpty = false
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)
            .get()
            .addOnSuccessListener {
                if(it.isEmpty) {
                    isBasketEmpty = true
                    Log.d("FIRESTOREdb", "BASKET IS EMPTY")
                }
                else{
                    Log.d("FIRESTOREdb", "BASKET IS NOT EMPTY")
                }
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE IN IS GOOD EXISTS FUN")
            }
            .await()          // wait for query finished
        return isBasketEmpty  // true if good is in DB
    }

    suspend fun retrieveGoodListFromBasket(): MutableList<Good> {
        var goodsList: MutableList<Good> = mutableListOf()
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    goodsList.add(document.toObject(Good::class.java))
                }
                Log.d("FIRESTOREdb", "RETRIEVED FROM BASKET GOODS LIST: $goodsList")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE IN RETRIEVE GOODS LIST BASKET FUN") }
            .await()                                                    // wait for query finished
        Log.d("FIRESTOREdb", "mutableListOf: $goodsList")
        return goodsList
    }

    fun saveOrderInDB(order: OrderObject){
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_ORDERS).document(order.orderId)                // creating document called orderId
            .set(order)                                                                 // save order in document
            .addOnSuccessListener {
                clearUsersBasket()                   // after successful order --> clear basket
                Log.d("FIRESTOREdb", "ORDER IS SAVED IN DB")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE!!!. ORDER IS NOT SAVED IN DB") }
    }


    //admin check. returns true if current user is admin
    suspend fun isCurrentUserAdmin(): Boolean{
        var isAdmin: Boolean = false
        val currentUserUid = Firebase.auth.currentUser.uid
        if (currentUserUid != null){
            firestore.collection(COLLECTION_ADMINS).get()       // get admin querySnapshot
                .addOnSuccessListener {
                    for (adminUid in it){
                        if (adminUid.id == currentUserUid){     // current user == remote admin
                            isAdmin = true
                        }
                        Log.d("FIREBASEdb", adminUid.id)
                    }
                }
                .addOnFailureListener { Log.d("FIREBASEdb", "FAIL") }
                .await()        // wait for query finished
        }
        return isAdmin
    }
}