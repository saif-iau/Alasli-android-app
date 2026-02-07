package com.example.alasli.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alasli.data.enums.OrderStatus
import com.example.alasli.data.enums.PaymentMethod
import com.example.alasli.data.enums.PaymentSplit


@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val clientName: String,
    val phoneNumber: String,
    val invoiceNumber: Int,
    val qty: Int,
    val total: Double,
    val paymentMethod: PaymentMethod,
    val paymentSplit: PaymentSplit,
    val status: OrderStatus,
    val orderDate: String
)
