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

    // collection names
    private const val COLLECTION_USERS = "users"
    private const val COLLECTION_ADMINS = "administrators"
    private const val COLLECTION_USERS_BASKET = "users basket"
    private const val COLLECTION_USERS_ORDERS = "users_orders"


    /**Save local users in remote database*/
    fun saveUserInDB(user: UserObject) {
        firestore.collection(COLLECTION_USERS)
            .document(Firebase.auth.currentUser.uid)                // creating document called User.uid in Firestore
            .set(user)                                              // saving user info in document
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "SAVED IN DB")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", " FAILURE SAVING IN DB") }
    }

    /**Get user info from remote database*/
    fun retrieveUserFromDB() {
        val uid = Firebase.auth.currentUser.uid
        firestore.collection(COLLECTION_USERS).document(uid)
            .get()                                              // get user from DB USERS collection
            .addOnSuccessListener {
                UserObject.receiveRemoteUser(it)                      // saving data to local user object
                Log.d("FIRESTOREdb", "RETRIEVED FROM DB")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", " FAILURE RETRIEVING USER FROM DB") }

    }

    /**Get list of users from remote database*/
    suspend fun retrieveListOfUsers(): List<User>{
        var usersList: MutableList<User> = mutableListOf()
        firestore.collection(COLLECTION_USERS)
            .get()                                              // get list of users
            .addOnSuccessListener {
                for(user in it){
                    usersList.add(user.toObject(User::class.java))
                }
                Log.d("FIRESTOREdb", "LIST OF USERS RETRIEVED FROM DB")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE RETRIEVING LIST OF USERS FROM DB") }
            .await()            // wait for query finished
        return usersList
    }

    /**Save local good to remote users basket*/
    fun saveGoodInUsersBasket(good: GoodObject) {
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(good.goodId)                      // path
            .set(good)                                                                      // set good
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "GOOD IS SUCCESSFULLY SAVED IN USERS_BASKET")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT SAVED IN USERS_BASKET")
                showToast("Failure")
            }
    }

    /**Delete good from remote users basket*/
    fun deleteGoodFromUsersBasket(good: GoodObject) {
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(good.goodId)                      // path
            .delete()                                                                       // delete document (good)
            .addOnSuccessListener {
                Log.d("FIRESTOREdb", "GOOD SUCCESSFULLY DELETED FROM USERS_BASKET")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT DELETED FROM USERS_BASKET")
                showToast("Failure")
            }
    }

    /**Delete all goods from remote users basket*/
    fun clearUsersBasket() {

        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)                                                    // path
            .get()                                                           // get list of goods in remote basket
            .addOnSuccessListener {
                for (document in it) {                                      // delete every document
                    firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
                        .collection(COLLECTION_USERS_BASKET).document(document.id)                  // path
                        .delete()
                }
                Log.d("FIRESTOREdb", "GOOD SUCCESSFULLY DELETED FROM USERS_BASKET")
            }
            .addOnFailureListener {
                Log.d("FIRESTOREdb", "FAILURE. GOOD IS NOT DELETED FROM USERS_BASKET")
                showToast("Failure")
            }
    }

    /**Check if good is already in remote users basket*/
    suspend fun checkIsGoodAlreadyInBasket(): Boolean {
        var isGoodInBasket = false
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET).document(GoodObject.goodId)                // path
            .get()                                                                          // get document
            .addOnSuccessListener {
                if (it.exists()) {                          // if it exists return true
                    isGoodInBasket = true
                    Log.d("FIRESTOREdb", "GOOD IS ALREADY EXISTS IN USERS_BASKET")
                } else {
                    Log.d("FIRESTOREdb", "GOOD DOESN'T IN USERS_BASKET")
                }
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE IN IS GOOD EXISTS FUN") }
            .await()            // wait for query finished
        return isGoodInBasket  // true if good is in DB
    }

    /**Check if remote basket is empty*/
    suspend fun checkIsBasketEmpty(): Boolean {
        var isBasketEmpty = false
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)                                        // path
            .get()                                                     // get all items from remote basket
            .addOnSuccessListener {
                if (it.isEmpty) {                                       // return true if it is emty
                    isBasketEmpty = true
                    Log.d("FIRESTOREdb", "BASKET IS EMPTY")
                } else {
                    Log.d("FIRESTOREdb", "BASKET IS NOT EMPTY")
                }
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE IN IS GOOD EXISTS FUN") }
            .await()          // wait for query finished
        return isBasketEmpty  // true if good is in DB
    }

    /**Get list of goods from remote users basket*/
    suspend fun retrieveGoodListFromBasket(): MutableList<Good> {
        var goodsList: MutableList<Good> = mutableListOf()
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_BASKET)                                        // path
            .get()                                                  // get list of goods
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    goodsList.add(document.toObject(Good::class.java))
                }
                Log.d("FIRESTOREdb", "RETRIEVED FROM BASKET GOODS LIST: $goodsList")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE IN RETRIEVE GOODS LIST BASKET FUN") }
            .await()                                                    // wait for query finished
        Log.d("FIRESTOREdb", "mutableListOf: $goodsList")
        return goodsList
    }

    /**Save order info (user data and goods in basket) in remote database*/
    fun saveOrderInDB(order: OrderObject) {
        firestore.collection(COLLECTION_USERS).document(Firebase.auth.currentUser.uid)
            .collection(COLLECTION_USERS_ORDERS)    // path
            .document(order.orderId)                // creating document called orderId
            .set(order)                             // save order in document
            .addOnSuccessListener {
                clearUsersBasket()                   // after successful order --> clear basket
                Log.d("FIRESTOREdb", "ORDER IS SAVED IN DB")
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE!!!. ORDER IS NOT SAVED IN DB") }
    }

    suspend fun retrieveUserOrdersList(uid: String): List<Order>{
        var orderList: MutableList<Order> = mutableListOf()
        firestore.collection(COLLECTION_USERS).document(uid)
            .collection(COLLECTION_USERS_ORDERS)
            .get()
            .addOnSuccessListener {
                for (order in it){
                    orderList.add(order.toObject(Order::class.java))
                }
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE!!!. ORDER LIST IS NOT RETRIEVED FROM DB") }
            .await()
        return orderList
    }


    /**Admin check. returns true if current user is admin*/
    suspend fun isCurrentUserAdmin(): Boolean {
        var isAdmin: Boolean = false
        val currentUserUid = Firebase.auth.currentUser?.uid
        if (currentUserUid != null) {
            firestore.collection(COLLECTION_ADMINS).get()       // get admin querySnapshot
                .addOnSuccessListener {
                    for (adminUid in it) {
                        if (adminUid.id == currentUserUid) {     // current user == remote admin
                            isAdmin = true
                            Administrator.receiveRemoteAdmin(adminUid)
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