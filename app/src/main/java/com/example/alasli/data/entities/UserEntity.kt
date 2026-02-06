package com.example.alasli.data.entities

import androidx.room.Entity

@Entity(tableName = "users")
class UserEntity(
    val name: String,
    val email: String,
    val password: String
) : BaseEntity()
