package com.example.pizzaproject.model

data class Product(
    val product_id: Int,
    val name_product: String,
    val category_id: Int,
    val description: String,
    val price: Int,
    val image: String,
)
