package com.example.pizzaproject.model.ingredient

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("ingredient_id"    ) var ingredientId    : Int?    = null,
    @SerializedName("ingredient_name"  ) var ingredientName  : String? = null,
    @SerializedName("ingredient_cost"  ) var ingredientCost  : Int?    = null,
    @SerializedName("ingredient_photo" ) var ingredientPhoto : String? = null
    )
