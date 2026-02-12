package com.example.alasli.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.alasli.data.entities.OrderEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to create DataStore instance
private val Context.dataStore by preferencesDataStore(name = "alasli_datastore")

class OrderDataStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val ORDERS_KEY = stringPreferencesKey("orders_list")
    }

    // Get all orders as Flow (auto-updates UI)
    val allOrders: Flow<List<OrderEntity>> = context.dataStore.data.map { preferences ->
        val json = preferences[ORDERS_KEY] ?: "[]"
        val type = object : TypeToken<List<OrderEntity>>() {}.type
        gson.fromJson<List<OrderEntity>>(json, type) ?: emptyList()
    }

    // Add a new order
    suspend fun addOrder(order: OrderEntity) {
        context.dataStore.edit { preferences ->
            val currentJson = preferences[ORDERS_KEY] ?: "[]"
            val type = object : TypeToken<MutableList<OrderEntity>>() {}.type
            val orders: MutableList<OrderEntity> = gson.fromJson(currentJson, type) ?: mutableListOf()

            // Add new order with auto-incremented ID
            val newOrder = order.copy(id = (orders.maxOfOrNull { it.id } ?: 0) + 1)
            val newJson = gson.toJson(orders)

            orders.add(newOrder)

            preferences[ORDERS_KEY] = gson.toJson(orders)

            Log.d("OrderDataStore", "Saved order: $newOrder")
            Log.d("OrderDataStore", "Total orders: ${orders.size}")
            Log.d("OrderDataStore", "All orders JSON: $newJson")
        }
    }

    // Delete an order
    suspend fun deleteOrder(order: OrderEntity) {
        context.dataStore.edit { preferences ->
            val currentJson = preferences[ORDERS_KEY] ?: "[]"
            val type = object : TypeToken<MutableList<OrderEntity>>() {}.type
            val orders: MutableList<OrderEntity> = gson.fromJson(currentJson, type) ?: mutableListOf()

            orders.removeIf { it.id == order.id }

            preferences[ORDERS_KEY] = gson.toJson(orders)
        }
    }

    // Update an order
    suspend fun updateOrder(order: OrderEntity) {
        context.dataStore.edit { preferences ->
            val currentJson = preferences[ORDERS_KEY] ?: "[]"
            val type = object : TypeToken<MutableList<OrderEntity>>() {}.type
            val orders: MutableList<OrderEntity> = gson.fromJson(currentJson, type) ?: mutableListOf()

            val index = orders.indexOfFirst { it.id == order.id }
            if (index != -1) {
                orders[index] = order
            }

            preferences[ORDERS_KEY] = gson.toJson(orders)
        }
    }

    // Clear all orders
    suspend fun clearAllOrders() {
        context.dataStore.edit { preferences ->
            preferences[ORDERS_KEY] = "[]"
        }
    }
}