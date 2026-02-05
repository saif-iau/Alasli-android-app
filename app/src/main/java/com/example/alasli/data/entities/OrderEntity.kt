package data.entities

import BaseEntity
import PaymentSplit
import PaymentMethod
import OrderStatus


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
