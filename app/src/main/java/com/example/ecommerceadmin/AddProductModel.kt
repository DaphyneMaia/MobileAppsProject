package com.example.ecommerceadmin

data class AddProductModel(
    val productName: String? = "",
    val productDescription: String? = "",
    val productCoverImg: String? = "",
    val productCategory: String? = "",
    val productId: String? = "",
    val productMRP: String? = "",
    val productSp: String? = "",
    val productImages: ArrayList<String>
)
