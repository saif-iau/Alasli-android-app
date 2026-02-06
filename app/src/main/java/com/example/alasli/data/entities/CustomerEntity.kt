package com.example.alasli.data.entities

import androidx.room.Entity

@Entity(tableName = "customers")
data class CustomerEntity (
    val name: String,
    val email: String
): BaseEntity()