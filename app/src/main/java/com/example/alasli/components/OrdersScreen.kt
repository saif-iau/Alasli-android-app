package com.example.alasli.screens

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.alasli.components.OrderDetailsPage
import com.example.alasli.components.OrdersGrid
import data.entities.OrderEntity

@Composable
fun OrdersScreen(
    orders: List<OrderEntity>,
    modifier: Modifier = Modifier
) {
    // Track selected order
    var selectedOrder by remember { mutableStateOf<OrderEntity?>(null) }

    // Show grid or details based on selection
    if (selectedOrder == null) {
        // Show orders grid
        OrdersGrid(
            orders = orders,
            onOrderClick = { order ->
                selectedOrder = order
            },
            modifier = modifier
        )
    } else {
        // Show order details
        OrderDetailsPage(
            order = selectedOrder!!,
            onBack = {
                selectedOrder = null
            },
            modifier = modifier
        )
    }
}