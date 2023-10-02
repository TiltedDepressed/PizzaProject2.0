package com.example.pizzaproject.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.pizzaproject.adapters.CategoriesAdapter
import com.example.pizzaproject.adapters.ProductsAdapter
import com.example.pizzaproject.databinding.ActivityMainBinding
import com.example.pizzaproject.datasource.ServiceBuilder
import com.example.pizzaproject.interfaces.APIServiceInterface
import com.example.pizzaproject.model.category.ApiResponseCategory
import com.example.pizzaproject.model.category.Category
import com.example.pizzaproject.model.product.ApiResponseProduct
import com.example.pizzaproject.model.product.Product

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var categoryList = ArrayList<Category>()
    var productList = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getAllCategories()
        getAllProducts()
    }
    private fun getAllCategories() {
        val retrofit = ServiceBuilder.buildService(APIServiceInterface::class.java)
        retrofit.getCategories().enqueue(object: Callback<ApiResponseCategory> {
            override fun onResponse(call: Call<ApiResponseCategory>, response: Response<ApiResponseCategory>) {
                try {
                    val responseBody = response.body()!!
                    categoryList = responseBody.data
                    val adapter = CategoriesAdapter(categoryList)
                    binding.recyclerView.adapter = adapter
                }
                catch (ex:java.lang.Exception){
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponseCategory>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
            }
        })
    }

    private fun getAllProducts(){
       val retrofit = ServiceBuilder.buildService(APIServiceInterface::class.java)
        retrofit.getProducts().enqueue(object: Callback<ApiResponseProduct>{
            override fun onResponse(call: Call<ApiResponseProduct>, response: Response<ApiResponseProduct>){
                try {
                    val responseBody = response.body()!!
                    productList = responseBody.data
                    val adapter = ProductsAdapter(productList)
                    binding.productsRecycler.adapter = adapter
                } catch (ex:java.lang.Exception){
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
            }
        })


    }
}

