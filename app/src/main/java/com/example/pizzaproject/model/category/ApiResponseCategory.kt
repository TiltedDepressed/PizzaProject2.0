package com.example.pizzaproject.model.category
import com.google.gson.annotations.SerializedName

data class ApiResponseCategory(

    @SerializedName("data"    ) var data    : ArrayList<Category> = arrayListOf(),
    @SerializedName("success" ) var success : Boolean?        = null

)
