package com.example.pizzaproject.model
import com.google.gson.annotations.SerializedName

data class ApiResponse(

    @SerializedName("data"    ) var data    : ArrayList<Category> = arrayListOf(),
    @SerializedName("success" ) var success : Boolean?        = null

)
