package com.example.alasli.data.repos

import android.content.Context
import com.example.alasli.data.OrderDataStore
import com.example.alasli.data.entities.OrderEntity
import kotlinx.coroutines.flow.Flow

class OrderRepository(context: Context) {

    private val dataStore = OrderDataStore(context)

    // Get all orders (returns Flow for automatic UI updates)
    fun getAllOrders(): Flow<List<OrderEntity>> = dataStore.allOrders

    // Insert new order
    suspend fun insertOrder(order: OrderEntity) {
        dataStore.addOrder(order)
    }

    // Delete order
    suspend fun deleteOrder(order: OrderEntity) {
        dataStore.deleteOrder(order)
    }

    // Update order
    suspend fun updateOrder(order: OrderEntity) {
        dataStore.updateOrder(order)
    }

    // Clear all orders
    suspend fun clearAll() {
        dataStore.clearAllOrders()
    }
}