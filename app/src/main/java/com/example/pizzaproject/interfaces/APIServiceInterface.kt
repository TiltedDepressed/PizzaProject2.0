package com.example.pizzaproject.interfaces

import com.example.pizzaproject.model.Category
import com.example.pizzaproject.model.Client
import com.example.pizzaproject.model.Ingredient
import com.example.pizzaproject.model.Order
import com.example.pizzaproject.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServiceInterface {


     /* ingredients */

     @GET("ingredients")
     suspend fun getIngredients(): List<Ingredient>

     @GET("ingredients/{product_id}")
     suspend fun getIngredientsByProductId(@Path("product_id") id : Int) : Ingredient?

     /* products */

     @GET("products")
     suspend fun getProducts(): List<Product>

     @GET("products/{products_id}")
     suspend fun getProductsById(@Path("products_id") id : Int): Product?

     @GET("products/categories/{category_id}")
     suspend fun getProductsCategoriesByCategoryId(@Path("category_id") id : Int): Product?

     /* categories */

     @GET("categories")
     suspend fun getCategories(): List<Category>

     /* clients */
     @GET("clients")
     suspend fun getClients(): List<Client>

     /* orders */
     @GET("orders")
     suspend fun getOrders(): List<Order>

     @GET("orders/{idOrder}")
     suspend fun getOrderByOrderId(@Path("order_id") id : Int): Order




}