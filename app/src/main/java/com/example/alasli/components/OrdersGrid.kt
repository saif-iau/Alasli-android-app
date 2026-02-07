package com.example.alasli.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alasli.data.entities.OrderEntity

@Composable
fun OrdersGrid(
    orders: List<OrderEntity>,
    onOrderClick: (OrderEntity) -> Unit,  // Add this parameter
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var currentPage by remember { mutableStateOf(0) }
    val itemsPerPage = 5

    // Filter orders based on search query
    val filteredOrders = remember(orders, searchQuery) {
        if (searchQuery.isBlank()) {
            orders
        } else {
            orders.filter { order ->
                order.id.toString().contains(searchQuery, ignoreCase = true) ||
                        order.customerId?.contains(searchQuery, ignoreCase = true) == true
            }
        }
    }

    // Calculate pagination
    val totalPages = remember(filteredOrders) {
        (filteredOrders.size + itemsPerPage - 1) / itemsPerPage
    }

    val paginatedOrders = remember(filteredOrders, currentPage) {
        val startIndex = currentPage * itemsPerPage
        val endIndex = minOf(startIndex + itemsPerPage, filteredOrders.size)
        if (startIndex < filteredOrders.size) {
            filteredOrders.subList(startIndex, endIndex)
        } else {
            emptyList()
        }
    }

    // Reset to first page when search query changes
    LaunchedEffect(searchQuery) {
        currentPage = 0
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search orders...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            singleLine = true
        )

        // Results info
        Text(
            text = "Showing ${paginatedOrders.size} of ${filteredOrders.size} orders",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(
                items = paginatedOrders,
                key = { it.id }
            ) { order ->
                OrderCard(
                    order = order,
                    onClick = onOrderClick  // Pass the click handler
                )
            }
        }

        // Pagination Controls
        if (totalPages > 1) {
            PaginationControls(
                currentPage = currentPage,
                totalPages = totalPages,
                onPageChange = { newPage -> currentPage = newPage },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun PaginationControls(
    currentPage: Int,
    totalPages: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous button
        IconButton(
            onClick = { onPageChange(currentPage - 1) },
            enabled = currentPage > 0
        ) {
            Text("←")
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Page indicators
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val visiblePages = getVisiblePages(currentPage, totalPages)

            visiblePages.forEachIndexed { index, page ->
                when {
                    page == -1 -> Text("...", style = MaterialTheme.typography.bodyMedium)
                    else -> {
                        FilledTonalButton(
                            onClick = { onPageChange(page) },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = if (page == currentPage)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Text("${page + 1}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Next button
        IconButton(
            onClick = { onPageChange(currentPage + 1) },
            enabled = currentPage < totalPages - 1
        ) {
            Text("→")
        }
    }
}

// Helper function to show page numbers with ellipsis
private fun getVisiblePages(currentPage: Int, totalPages: Int): List<Int> {
    if (totalPages <= 7) {
        return (0 until totalPages).toList()
    }

    val pages = mutableListOf<Int>()

    // Always show first page
    pages.add(0)

    when {
        currentPage <= 3 -> {
            // Show first 5 pages
            pages.addAll(1..4)
            pages.add(-1) // Ellipsis
            pages.add(totalPages - 1)
        }
        currentPage >= totalPages - 4 -> {
            // Show last 5 pages
            pages.add(-1) // Ellipsis
            pages.addAll((totalPages - 5) until totalPages)
        }
        else -> {
            // Show current page and neighbors
            pages.add(-1) // Ellipsis
            pages.addAll((currentPage - 1)..(currentPage + 1))
            pages.add(-1) // Ellipsis
            pages.add(totalPages - 1)
        }
    }

    return pages
}