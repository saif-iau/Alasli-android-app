package com.example.alasli.data.entities

import com.example.alasli.data.enums.OrderStatus
import com.example.alasli.data.enums.PaymentMethod
import com.example.alasli.data.enums.PaymentSplit
import java.util.Date

data class OrderEntity(
    val id: Int = 0,
    val clientName: String,
    val phoneNumber: String,
    val invoiceNumber: Int,
    val qty: Int,
    val total: Double,
    val paymentMethod: PaymentMethod,
    val paymentSplit: PaymentSplit,
    val status: OrderStatus,
    val placeDate: Date,
    val pickupDate: Date? = null
)