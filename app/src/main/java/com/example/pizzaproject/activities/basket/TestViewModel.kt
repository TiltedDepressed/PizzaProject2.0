package com.example.pizzaproject.activities.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaproject.model.product.Product



class TestViewModel: ViewModel() {
       val currentProduct: MutableLiveData<Product> by lazy{
           MutableLiveData<Product>()
       }
 }
