package com.example.alasli

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alasli.components.AddOrderForm
import com.example.alasli.components.OrderDetailsPage
import com.example.alasli.components.OrdersGrid
import data.entities.OrderEntity

@Composable
fun ActiveOrdersScreen() {
    var showAddForm by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<OrderEntity?>(null) }

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

    // Show different screens based on state
    when {
        // Show order details
        selectedOrder != null -> {
            OrderDetailsPage(
                order = selectedOrder!!,
                onBack = { selectedOrder = null }
            )
        }

        // Show add form or orders grid
        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                // ─── Header with button ───
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(onClick = { showAddForm = !showAddForm }) {
                        Icon(
                            if (showAddForm) Icons.Default.ArrowBack else Icons.Default.Add,
                            contentDescription = if (showAddForm) "Back" else "Add Order"
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(if (showAddForm) "Back to Orders" else "Add Order")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ─── Main content ───
                if (showAddForm) {
                    AddOrderForm(
                        onSave = {
                            /* handle save */
                            showAddForm = false
                        }
                    )
                } else {
                    OrdersGrid(
                        orders = fakeOrders(),
                        onOrderClick = { order ->
                            selectedOrder = order
                        }
                    )
                }
            }
        }
    }
}