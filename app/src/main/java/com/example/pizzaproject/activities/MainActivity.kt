package com.example.pizzaproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.pizzaproject.adapters.CategoriesAdapter
import com.example.pizzaproject.databinding.ActivityMainBinding
import com.example.pizzaproject.datasource.ServiceBuilder
import com.example.pizzaproject.interfaces.APIServiceInterface
import com.example.pizzaproject.model.ApiResponse
import com.example.pizzaproject.model.Category

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var newData = ArrayList<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getAllCategories()
    }
    private fun getAllCategories() {
        val retrofit = ServiceBuilder.buildService(APIServiceInterface::class.java)
        retrofit.getCategories().enqueue(object: Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                try {
                    val responseBody = response.body()!!
                    newData = responseBody.data
                    val adapter = CategoriesAdapter(newData)
                    binding.recyclerView.adapter = adapter
                }
                catch (ex:java.lang.Exception){
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
            }
        }
        )
    }
}