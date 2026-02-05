package com.example.alasli

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.alasli.components.OrdersGrid
import data.entities.OrderEntity

@Composable
fun ActiveOrdersScreen() {

    fun fakeOrders(): List<OrderEntity> =
        List(10) {
            OrderEntity(
                qty = 2,
                total = 150.0,
                customerId = "CUST$it",
                measurementId = "MEAS$it",
                invoiceId = "INV$it",
                paymentMethod = PaymentMethod.Card,
                paymentSplit = PaymentSplit.Full,
                orderStatus = OrderStatus.Completed,
                orderDate = System.currentTimeMillis(),
                pickupDate = System.currentTimeMillis() + 86400000
            )
        }

    OrdersGrid(
        orders = fakeOrders()

    )
}