package com.example.pizzaproject.interfaces

import com.example.pizzaproject.model.Client
import com.example.pizzaproject.model.ingredient.Ingredient
import com.example.pizzaproject.model.Order
import com.example.pizzaproject.model.category.ApiResponseCategory
import com.example.pizzaproject.model.ingredient.ApiResponseIngredient
import com.example.pizzaproject.model.product.ApiResponseProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIServiceInterface {


     /* ingredients */

     @GET("ingredients")
      fun getIngredients(): Call<ApiResponseIngredient>

     @GET("ingredients/{product_id}")
      fun getIngredientsByProductId(@Path("product_id") id : Int) : Call<ApiResponseIngredient>

     /* products */

     @Headers("Content-Type:application/json")
     @GET("products")
      fun getProducts(): Call<ApiResponseProduct>

     @Headers("Content-Type:application/json")
     @GET("products/{product_id}")
     fun getProductsById(@Path("product_id") id : Int): Call<ApiResponseProduct>

     @Headers("Content-Type:application/json")
     @GET("products/categories/{category_id}")
      fun getProductsByCategoriesId(@Path("category_id") id : Int): Call<ApiResponseProduct>

     /* categories */


     @Headers("Content-Type:application/json")
     @GET("categories")
      fun getCategories(): Call<ApiResponseCategory>

     /* clients */
     @GET("clients")
     suspend fun getClients(): List<Client>

     /* orders */
     @GET("orders")
     suspend fun getOrders(): List<Order>

     @GET("orders/{idOrder}")
     suspend fun getOrderByOrderId(@Path("order_id") id : Int): Order




}