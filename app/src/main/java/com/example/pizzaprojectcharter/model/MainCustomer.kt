package com.example.pizzaprojectcharter.model


import com.google.gson.annotations.SerializedName

data class MainCustomer(
    @SerializedName("order")
    val order: List<Order>
)