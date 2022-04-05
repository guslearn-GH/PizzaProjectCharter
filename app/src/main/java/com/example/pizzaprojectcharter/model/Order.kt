package com.example.pizzaprojectcharter.model


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("sauce")
    val sauce: List<String>?,
    @SerializedName("size")
    val size: String,
    @SerializedName("toppings")
    val toppings: List<String>,
    @SerializedName("type")
    val type: String,
    var price: String? = null
)