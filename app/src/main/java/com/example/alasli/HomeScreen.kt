package com.example.alasli

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenWithNav() {
    var selectedItem by remember { mutableStateOf("Active Orders") }

    Row(modifier = Modifier.fillMaxSize()) {
        // Side Navigation
        SideNav(
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it }
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(
                    text = selectedItem,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(24.dp))

                when (selectedItem) {
                    "Active Orders" -> ActiveOrdersScreen()
                    "History" -> HistoryScreen()
                }
            }
        }
    }
}

@Composable
fun SideNav(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(240.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Alasli",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            NavItem(Icons.Default.DateRange, "Active Orders", selectedItem, onItemSelected)
            NavItem(Icons.Default.Refresh, "History", selectedItem, onItemSelected)



        }
    }
}

@Composable
fun NavItem(
    icon: ImageVector,
    label: String,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    val isSelected = selectedItem == label
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceContainer
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onItemSelected(label) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}