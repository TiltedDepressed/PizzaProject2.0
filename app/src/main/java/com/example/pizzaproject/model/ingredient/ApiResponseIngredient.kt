package com.example.pizzaproject.model.ingredient

import com.google.gson.annotations.SerializedName

data class ApiResponseIngredient(
    @SerializedName("data"    ) var data    : ArrayList<Ingredient> = arrayListOf(),
    @SerializedName("success" ) var success : Boolean?        = null
)
