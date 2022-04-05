package com.example.pizzaprojectcharter.utils

import java.lang.Exception

private const val EURO = "\u20ac"
enum class PizzaSize(val price: String) {
    SMALL("4${EURO}"),
    MEDIUM("8${EURO}"),
    LARGE("15${EURO}");

    companion object {
        fun getRealPizzaSize(pizzaSize: String): PizzaSize? {
            return try {
                valueOf(pizzaSize.uppercase())
            } catch (e: Exception) {
                null
            }
        }
    }
}