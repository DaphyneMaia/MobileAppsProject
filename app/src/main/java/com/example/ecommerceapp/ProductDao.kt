package com.example.ecommerceapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: ProductModel) {
    }

    @Delete
    suspend fun deleteProduct(product: ProductModel) {
    }

    @Query("SELECT * FROM products")
    fun getAllProducts() : LiveData<List<ProductModel>> {
        return TODO("Provide the return value")
    }

    @Query("SELECT * FROM products WHERE productId = :id")
    fun isExit (id: String) : ProductModel {
        return TODO("Provide the return value")
    }
}