package com.example.pizzaprojectcharter.network

import com.example.pizzaprojectcharter.model.Order

sealed interface OrderState {
    object LOADING : OrderState
    class SUCCESS(val orders: List<Order>) : OrderState
    class ERROR(val error: Throwable) : OrderState
}