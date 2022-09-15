package com.kirson.corporateapp.orders.data.network

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

sealed class OrderResponse {
  data class OnSuccess(val querySnapshot: QuerySnapshot?): OrderResponse()
  data class OnError(val exception: FirebaseFirestoreException?): OrderResponse()
}