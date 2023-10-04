package com.example.pizzaproject.datasource

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://188.234.244.32:8090/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }


    private val images = Retrofit.Builder()
        .baseUrl("http://188.234.244.32:8090/images/product/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> imagesService(service: Class<T>): T {
        return images.create(service)
    }




}