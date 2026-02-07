package com.example.alasli

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alasli.components.AddOrderForm
import com.example.alasli.components.OrderDetailsPage
import com.example.alasli.components.OrdersGrid
import com.example.alasli.data.entities.OrderEntity
import com.example.alasli.data.enums.OrderStatus
import com.example.alasli.data.enums.PaymentMethod
import com.example.alasli.data.enums.PaymentSplit
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ActiveOrdersScreen() {
    var showAddForm by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<OrderEntity?>(null) }

    fun fakeOrders(): List<OrderEntity> =
        List(10) { i ->
            OrderEntity(
                id = i,
    clientName = "John Doe",
    phoneNumber = "+966512345678",
    invoiceNumber = 1001,
    qty = 2,
    total = 150.0,
    paymentMethod = PaymentMethod.Card,
    paymentSplit = PaymentSplit.Full,
    status = OrderStatus.Completed,
    placeDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2026-02-08")!!)
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