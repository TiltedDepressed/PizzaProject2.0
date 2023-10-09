package com.example.pizzaproject.model.product

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id_product"   ) var idProduct   : Int,
    @SerializedName("name_product" ) var nameProduct : String? = null,
    @SerializedName("category_id"  ) var categoryId  : Int?    = null,
    @SerializedName("description"  ) var description : String? = null,
    @SerializedName("price"        ) var price       : Int,
    @SerializedName("image"        ) var image       : String? = null
)
