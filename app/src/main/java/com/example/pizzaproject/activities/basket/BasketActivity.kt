package com.example.pizzaproject.activities.basket


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.pizzaproject.databinding.ActivityBasketBinding


class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    lateinit var viewModel: TestViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.idProduct.text = intent.getStringExtra("productId")

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)




    }


}