package com.example.pizzaproject.model

import com.example.pizzaproject.interfaces.APIServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object{
        private const val BASE_URL = "http://188.234.244.32:8090/api/"
        @Volatile
        private var INSTANCE: APIServiceInterface? = null

        fun getService(): APIServiceInterface{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildService();
                }
            }
            return  INSTANCE!!
        }

        private fun buildService():APIServiceInterface{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(APIServiceInterface::class.java)
        }

    }
}