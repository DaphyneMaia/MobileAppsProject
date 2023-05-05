package com.example.ecommerceapp

data class AddProductModel(
    val productName: String? = "",
    val productDescription: String? = "",
    val productCoverImg: String? = "",
    val productCategory: String? = "",
    val productId: String? = "",
    val productMRP: String? = "",
    val productSp: String? = "",
    val productImage: ArrayList<String> = ArrayList()
)
