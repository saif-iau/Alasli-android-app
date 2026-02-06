package com.example.alasli

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alasli.components.AddOrderForm
import com.example.alasli.components.OrdersGrid
import com.example.alasli.data.entities.OrderEntity
import com.example.alasli.data.enums.OrderStatus
import com.example.alasli.data.enums.PaymentMethod
import com.example.alasli.data.enums.PaymentSplit

@Composable
fun ActiveOrdersScreen() {

    var showAddForm by remember { mutableStateOf(false) }

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

    Column(modifier = Modifier.fillMaxSize()) {

        // ─── Header with button ───
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Active Orders",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(onClick = { showAddForm = !showAddForm }) {
                Icon(Icons.Default.Add, contentDescription = "Add Order")
                Spacer(Modifier.width(8.dp))
                Text(if (showAddForm) "Back to Orders" else "Add Order")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ─── Main content ───
        if (showAddForm) {
            AddOrderForm(
                onSave = {
                    println("testing print")
//                    showAddForm = false
                }
            )
        } else {
            OrdersGrid(orders = fakeOrders())
        }
    }
}