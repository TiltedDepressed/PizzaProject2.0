package com.example.pizzaproject.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id_category" ) var id_category : Int,
    @SerializedName("category"    ) var category   : String
    )
