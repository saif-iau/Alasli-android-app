package com.example.alasli.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddOrderForm(
    onSave: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "New Order",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Customer ID") },
            singleLine = true
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Quantity") },
            singleLine = true
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Total") },
            singleLine = true
        )

        Button(onClick = onSave, modifier = Modifier.fillMaxWidth()) {
            Text("Save Order")
        }
    }
}