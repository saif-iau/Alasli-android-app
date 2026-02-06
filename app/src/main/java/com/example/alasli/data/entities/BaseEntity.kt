package com.example.alasli.data.entities

import androidx.room.PrimaryKey
import java.util.UUID

abstract class BaseEntity {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString() // Unique ID

    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    var createdById: String? = null
    var updatedById: String? = null

    var isActive: Boolean = true
    var isDeleted: Boolean = false
}
