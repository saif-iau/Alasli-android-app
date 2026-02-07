package com.example.alasli.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alasli.utils.formatDate
import com.example.alasli.data.entities.OrderEntity

@Composable
fun OrderCard(
    order: OrderEntity,
    modifier: Modifier = Modifier,
    onClick: ((OrderEntity) -> Unit)? = null  // Changed to pass the order
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable { onClick(order) }  // Pass order here
                else Modifier
            ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header
            Text(
                text = "Order #${order.id.take(6)}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Status row
            AssistChip(
                onClick = {},
                label = { Text(order.orderStatus.name) },
                shape = RoundedCornerShape(50),
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content block
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Qty: ${order.qty}")
                Text("Total: ${order.total} SAR")
                Text("Payment: ${order.paymentMethod.name}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Footer
            Text(
                text = "Pickup • ${formatDate(order.pickupDate)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}