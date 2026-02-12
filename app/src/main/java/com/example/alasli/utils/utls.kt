package com.example.alasli.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private fun formatDate(date: Date?): String {
    if (date == null) return "Not Set"
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(date)
}
