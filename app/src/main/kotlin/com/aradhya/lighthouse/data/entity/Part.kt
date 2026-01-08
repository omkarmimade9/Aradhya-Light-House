package com.aradhya.lighthouse.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parts")
data class Part(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val companyName: String,
    val category: String,
    val stockQuantity: Int,
    val description: String = "",
    val imageUrl: String = ""
)
