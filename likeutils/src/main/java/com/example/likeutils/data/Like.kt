package com.example.likeutils.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_items", primaryKeys = ["itemId", "userEmail"])
data class Like(
    val itemId: Long,
    val userEmail: String
)