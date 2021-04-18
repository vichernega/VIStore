package com.example.vistore.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.Order

class OrdersToConfirmRepository {

    private var _orderToConfirmListLiveData: MutableLiveData<List<Order>> = MutableLiveData()

    fun getOrderList() {
        var ordersToConfirmList: MutableList<Order> = mutableListOf()               // empty list for orders that need to be confirmed
        _orderToConfirmListLiveData.postValue(ordersToConfirmList)      // change liveData value (in case the list will be empty)

        FirebaseObject.firestore.collection(FirebaseObject.COLLECTION_USERS)
            .get()                                                                  // get all users from DB
            .addOnSuccessListener { userList ->
                for (user in userList) {                                            // for every user get all orders

                    FirebaseObject.firestore.collection(FirebaseObject.COLLECTION_USERS)
                        .document(user.id)                                          // every user
                        .collection(FirebaseObject.COLLECTION_USERS_ORDERS)
                        .get()                                                      // get all users orders
                        .addOnSuccessListener { ordersList ->

                            if (!ordersList.isEmpty) {                              // if list of orders is not empty check every order
                                for (order in ordersList) {
                                    // convert order (QueryDocumentSnapshot) to Order (Data class)
                                    // get value of field "confirmed", convert it to Bool
                                    if (!order.toObject(Order::class.java).confirmed.toBoolean()) {     // if order is not confirmed add it to list
                                        ordersToConfirmList.add(order.toObject(Order::class.java))
                                        _orderToConfirmListLiveData.postValue(ordersToConfirmList)      // change liveData value
                                    }
                                }
                            } else { Log.d("FIRESTOREdb", "EMPTY LIST") }
                        }
                        .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE!!!. USER ORDER LIST IS NOT RETRIEVED FROM DB") }
                }
            }
            .addOnFailureListener { Log.d("FIRESTOREdb", "FAILURE!!!. ALL ORDERS LIST IS NOT RETRIEVED FROM DB") }
    }

    val orderToConfirmListLiveData: MutableLiveData<List<Order>> get() = _orderToConfirmListLiveData
}