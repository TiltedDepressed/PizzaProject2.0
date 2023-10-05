package com.example.pizzaproject.activities.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.pizzaproject.R
import com.example.pizzaproject.activities.main.MainActivity
import com.example.pizzaproject.databinding.ActivityProductBinding
import com.example.pizzaproject.datasource.ServiceBuilder
import com.example.pizzaproject.interfaces.APIServiceInterface
import com.example.pizzaproject.model.product.Product
import retrofit2.Call


class ProductActivity : AppCompatActivity() {

     private lateinit var binding: ActivityProductBinding

     val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val productId = intent.getIntExtra(MainActivity.EXTRA_ID,0)
        getProductById(productId)
    }

    private fun getProductById(id: Int){

    }


}