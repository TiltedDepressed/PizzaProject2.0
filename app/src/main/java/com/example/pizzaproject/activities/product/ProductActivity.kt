package com.example.pizzaproject.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.pizzaproject.R
import com.example.pizzaproject.activities.main.MainActivity
import com.example.pizzaproject.adapters.CategoriesAdapter
import com.example.pizzaproject.adapters.ProductDetailAdapter
import com.example.pizzaproject.databinding.ActivityProductBinding
import com.example.pizzaproject.datasource.ServiceBuilder
import com.example.pizzaproject.interfaces.APIServiceInterface
import com.example.pizzaproject.model.category.ApiResponseCategory
import com.example.pizzaproject.model.product.ApiResponseProduct
import com.example.pizzaproject.model.product.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

     private lateinit var binding: ActivityProductBinding

     var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val productId = intent.getIntExtra(MainActivity.EXTRA_ID,0)
        getProductById(productId)
    }

    private fun getProductById(id: Int){
        val retrofit = ServiceBuilder.buildService(APIServiceInterface::class.java)
        retrofit.getProductsById(id).enqueue(object: Callback<ApiResponseProduct>{
            override fun onResponse(call: Call<ApiResponseProduct>, response: Response<ApiResponseProduct>){
                try{
                    val responseBody = response.body()!!
                    productList = responseBody.data
                    val adapter = ProductDetailAdapter(productList)
                    binding.productsRecycler.adapter = adapter
                }
                catch (ex:java.lang.Exception){
                    ex.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ApiResponseProduct>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
            }
        })
    }


}