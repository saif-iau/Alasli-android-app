package com.example.alasli.data.entities

import com.example.alasli.data.enums.EnumConverters
import com.example.alasli.data.enums.OrderStatus
import com.example.alasli.data.enums.PaymentMethod
import com.example.alasli.data.enums.PaymentSplit
import androidx.room.Entity
import androidx.room.TypeConverters


@Entity(tableName = "orders")
@TypeConverters(EnumConverters::class)
data class OrderEntity(
    var qty: Int,
    var total: Double,
    var customerId: String,
    var measurementId: String,
    var invoiceId: String,
    var paymentMethod: PaymentMethod,
    var paymentSplit: PaymentSplit,
    var orderStatus: OrderStatus,
    var orderDate: Long,
    var pickupDate: Long
) : BaseEntity()
