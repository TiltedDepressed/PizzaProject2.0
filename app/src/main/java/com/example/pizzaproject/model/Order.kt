package com.example.pizzaproject.model

data class Order(
    val id_order:Int,
    val id_client: Int,
    val date_order: String,
    val done : Int,
    val cost: Int,
    val phone: String,
    val address: String,

)
