package com.example.ecommerceapp

import android.media.Image
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    @NonNull
    val productId: String,
    @ColumnInfo("productName") val productName: String? = "",
    @ColumnInfo("productImages") val productImage: String? = "",
    @ColumnInfo("productSp") val productSp: String? = "",
)
