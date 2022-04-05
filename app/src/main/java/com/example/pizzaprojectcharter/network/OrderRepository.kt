package com.example.pizzaprojectcharter.network

import android.content.Context
import android.util.Log
import com.example.pizzaprojectcharter.model.MainCustomer
import com.google.gson.Gson

private const val TAG = "OrderRepository"

interface OrderRepository {
    suspend fun getOrders(context: Context): OrderState
}

class OrderRepositoryImpl : OrderRepository {

    override suspend fun getOrders(context: Context): OrderState {
        try {
            context.assets.open("order.json")
                .bufferedReader()
                .use { it.readText() }
                .also {
                    return OrderState.SUCCESS(Gson().fromJson(it, MainCustomer::class.java).order)
                }
        } catch (exception: Exception) {
            Log.e(TAG, "getOrders: ${exception.localizedMessage}", )
            return OrderState.ERROR(exception)
        }
    }
}