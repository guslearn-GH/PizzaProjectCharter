package com.example.pizzaprojectcharter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pizzaprojectcharter.model.Order
import com.example.pizzaprojectcharter.network.OrderRepository
import com.example.pizzaprojectcharter.network.OrderRepositoryImpl
import com.example.pizzaprojectcharter.network.OrderState
import com.example.pizzaprojectcharter.utils.PizzaSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val orderRepository: OrderRepository by lazy {
        OrderRepositoryImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            val orderState = orderRepository.getOrders(baseContext)
            withContext(Dispatchers.Main) {
                when(orderState) {
                    is OrderState.LOADING -> {
                        Log.d(TAG, "onResume: LOADING ORDERS FROM LOCAL JSON")
                    }
                    is OrderState.SUCCESS -> {
                        val orderWithPrices = calculatePizzaPrice(orderState.orders)
                        Log.d(TAG, "onResume: $orderWithPrices")
                    }
                    is OrderState.ERROR -> {
                        Log.e(TAG, "onResume: ${orderState.error.localizedMessage}", )
                    }
                }
            }
        }
    }

    private fun calculatePizzaPrice(orders: List<Order>): List<Order> {
        orders.map { order ->
            when(val pSize = PizzaSize.getRealPizzaSize(order.size)) {
                PizzaSize.SMALL -> {
                    order.price = pSize.price
                }
                PizzaSize.MEDIUM -> {
                    order.price = pSize.price
                }
                PizzaSize.LARGE -> {
                    order.price = pSize.price
                }
                else -> {
                    // no-op
                }
            }
        }
        return orders
    }
}