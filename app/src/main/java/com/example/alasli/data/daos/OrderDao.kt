package com.example.alasli.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alasli.data.entities.OrderEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(order: OrderEntity)

    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<OrderEntity>

    @Delete
    suspend fun delete(order: OrderEntity)
}