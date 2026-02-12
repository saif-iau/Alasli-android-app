package com.example.alasli

import OrderViewModel
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alasli.components.AddOrderForm
import com.example.alasli.components.OrderDetailsPage
import com.example.alasli.components.OrdersGrid
import com.example.alasli.data.entities.OrderEntity
import com.example.alasli.viewmodels.OrderViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ActiveOrdersScreen(
    viewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {
    var showAddForm by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<OrderEntity?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Collect orders from ViewModel
    val orders by viewModel.orders.collectAsState(initial = emptyList())

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
                        onSave = { order ->
                            scope.launch {
                                try {
                                    viewModel.addOrder(order)

                                    // Show success toast
                                    Toast.makeText(
                                        context,
                                        "Order saved successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Go back to grid
                                    showAddForm = false
                                } catch (e: Exception) {
                                    // Show error toast
                                    Toast.makeText(
                                        context,
                                        "Failed to save order: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                } else {
                    OrdersGrid(
                        orders = orders, // Use real orders from ViewModel
                        onOrderClick = { order ->
                            selectedOrder = order
                        }
                    )
                }
            }
        }
    }
}