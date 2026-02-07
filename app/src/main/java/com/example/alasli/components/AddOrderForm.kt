package com.example.alasli.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color

@Composable
fun AddOrderForm(
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    var customerName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var invoiceNumber by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf("Cash") }
    var selectedPaymentType by remember { mutableStateOf("Full") }

    // Validation states
    var customerNameError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    var invoiceNumberError by remember { mutableStateOf<String?>(null) }
    var showValidationErrors by remember { mutableStateOf(false) }

    // Validation functions
    fun validateCustomerName(name: String): String? {
        return when {
            name.isBlank() -> "Customer name is required"
            name.length < 2 -> "Name must be at least 2 characters"
            name.length > 100 -> "Name must be less than 100 characters"
            !name.matches(Regex("^[a-zA-Z\\s]+$")) -> "Name should only contain letters"
            else -> null
        }
    }

    fun validatePhoneNumber(phone: String): String? {
        return when {
            phone.isBlank() -> "Phone number is required"
            phone.length < 10 -> "Phone number must be at least 10 digits"
            phone.length > 15 -> "Phone number must be less than 15 digits"
            !phone.matches(Regex("^[0-9+\\-\\s()]+$")) -> "Invalid phone number format"
            else -> null
        }
    }



    fun validateInvoiceNumber(invoice: String): String? {
        return when {
            invoice.isBlank() -> "Invoice number is required"
            invoice.length < 3 -> "Invoice number must be at least 3 characters"
            invoice.length > 50 -> "Invoice number must be less than 50 characters"
            else -> null
        }
    }

    fun validateForm(): Boolean {
        customerNameError = validateCustomerName(customerName)
        phoneNumberError = validatePhoneNumber(phoneNumber)
        invoiceNumberError = validateInvoiceNumber(invoiceNumber)

        return customerNameError == null &&
                phoneNumberError == null &&
                invoiceNumberError == null
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "New Order",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Fill in the details below to create a new order",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider()

            // Customer Information Section
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionHeader(
                    title = "Customer Information",
                    icon = Icons.Default.Person
                )

                ValidatedTextField(
                    value = customerName,
                    onValueChange = {
                        customerName = it
                        if (showValidationErrors) {
                            customerNameError = validateCustomerName(it)
                        }
                    },
                    label = "Customer Name",
                    leadingIcon = Icons.Default.Person,
                    errorMessage = if (showValidationErrors) customerNameError else null,
                    isRequired = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ValidatedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            if (showValidationErrors) {
                                phoneNumberError = validatePhoneNumber(it)
                            }
                        },
                        label = "Phone Number",
                        leadingIcon = Icons.Default.Phone,
                        errorMessage = if (showValidationErrors) phoneNumberError else null,
                        isRequired = true,
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier.weight(1f)
                    )

                }
            }

            // Order Details Section
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                SectionHeader(
                    title = "Order Details",
                    icon = Icons.Default.ShoppingCart
                )

                ValidatedTextField(
                    value = invoiceNumber,
                    onValueChange = {
                        invoiceNumber = it
                        if (showValidationErrors) {
                            invoiceNumberError = validateInvoiceNumber(it)
                        }
                    },
                    label = "Invoice Number",
                    leadingIcon = Icons.Default.Info,
                    errorMessage = if (showValidationErrors) invoiceNumberError else null,
                    isRequired = true
                )

                // Payment Type Radio Group
                RadioButtonGroup(
                    title = "Payment Type",
                    options = listOf("Full", "Partial"),
                    selectedOption = selectedPaymentType,
                    onOptionSelected = { selectedPaymentType = it }
                )

                // Payment Method Radio Group
                RadioButtonGroup(
                    title = "Payment Method",
                    options = listOf("Cash", "Card"),
                    selectedOption = selectedPaymentMethod,
                    onOptionSelected = { selectedPaymentMethod = it }
                )
            }

            HorizontalDivider()

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Handle cancel */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        showValidationErrors = true
                        if (validateForm()) {
                            onSave()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Order")
                }
            }
        }
    }
}

@Composable
private fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    errorMessage: String?,
    isRequired: Boolean,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(label)
                    if (isRequired) {
                        Text(
                            text = " *",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            leadingIcon = {
                Icon(
                    leadingIcon,
                    contentDescription = null,
                    tint = if (errorMessage != null)
                        MaterialTheme.colorScheme.error
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            isError = errorMessage != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth(),
            supportingText = if (errorMessage != null) {
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                        Text(errorMessage)
                    }
                }
            } else null
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun RadioButtonGroup(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ) {
            Column(
                modifier = Modifier
                    .selectableGroup()
                    .padding(8.dp)
            ) {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (option == selectedOption),
                                onClick = { onOptionSelected(option) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (option == selectedOption)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}