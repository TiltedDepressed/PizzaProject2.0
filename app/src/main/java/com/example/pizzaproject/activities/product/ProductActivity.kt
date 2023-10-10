package com.example.pizzaproject.activities.product

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.activities.basket.BasketActivity
import com.example.pizzaproject.activities.main.MainActivity
import com.example.pizzaproject.adapters.IngredientsAdapter
import com.example.pizzaproject.databinding.ActivityProductBinding
import com.example.pizzaproject.datasource.ServiceBuilder
import com.example.pizzaproject.interfaces.APIServiceInterface
import com.example.pizzaproject.model.ingredient.ApiResponseIngredient
import com.example.pizzaproject.model.ingredient.Ingredient
import com.example.pizzaproject.model.product.ApiResponseProduct
import com.example.pizzaproject.model.product.Product
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

     private lateinit var binding: ActivityProductBinding

     var productList = ArrayList<Product>()

    var ingredientList = ArrayList<Ingredient>()

    var totalPriceArray = ArrayList<Int>()


    companion object{

        const val EXTRA_ID = "extra_id"
        const val COST = "COST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val backbutton: Button = findViewById(R.id.backButton)
        backbutton.setOnClickListener{
            val intent = Intent(this@ProductActivity,MainActivity::class.java)
            startActivity(intent)
        }

        val productId = intent.getIntExtra(MainActivity.EXTRA_ID,0)
        getProductById(productId)
    }

    private fun getProductById(id: Int) {
        val retrofit = ServiceBuilder.buildService(APIServiceInterface::class.java)
        retrofit.getProductsById(id).enqueue(object: Callback<ApiResponseProduct>{
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ApiResponseProduct>, response: Response<ApiResponseProduct>) {
                try{

                       /* size buttons container */
                    val sizeButtonsContainer : LinearLayout = findViewById(R.id.sizeButtonsContainer)
                    val sizeButtonSmall : Button = findViewById(R.id.sizeButtonSmall)
                    val sizeButtonMedium : Button = findViewById(R.id.sizeButtonMedium)
                    val sizeButtonBig : Button = findViewById(R.id.sizeButtonBig)

                        /* type buttons container  */
                    val typeButtonsContainer : LinearLayout = findViewById(R.id.typeButtonsContainer)
                    val typeButtonTraditional : Button = findViewById(R.id.typeButtonTraditional)
                    val typeButtonSlim : Button = findViewById(R.id.typeButtonSlim)

                    val totalPriceButton : Button = findViewById(R.id.totalPriceButton)
                    val addPizzaText : TextView = findViewById(R.id.AddPizzaText)
                    val size : TextView = findViewById(R.id.size)
                    val type: TextView = findViewById(R.id.type)
                    val weight: TextView = findViewById(R.id.weight)
                    val ingredientsRecycle : RecyclerView = findViewById(R.id.ingredientsRecyclerView)

                    val responseBody = response.body()!!
                    productList = responseBody.data
                    Picasso.get().load( "http:/172.30.44.151:8090/images/product/"+
                            productList[0].image).into(binding.pizzaImage)
                    binding.pizzaTitle.text=productList[0].nameProduct
                    binding.pizzaDescription.text = productList[0].description
                    totalPriceButton.text = "В КОРЗИНУ ЗА ${productList[0].price} ₽"

                    val totalPrice = productList[0].price
                    var newTotalPrice: Int
                    val onePercent  = totalPrice?.div(100)

                    /* Внимание слабонервным ниже не смотреть  */

                    retrofit.getIngredients().enqueue(object: Callback<ApiResponseIngredient>{
                        override fun onResponse(call: Call<ApiResponseIngredient>, response: Response<ApiResponseIngredient>){
                                val responseBody = response.body()!!
                                ingredientList = responseBody.data
                                val adapter = IngredientsAdapter(ingredientList){
                                    val intent = Intent(this@ProductActivity,ProductActivity::class.java)
                                    intent.putExtra(ProductActivity.COST,it)
                                    val cost = intent.getIntExtra(ProductActivity.COST,0)
                                }
                                binding.ingredientsRecyclerView.adapter = adapter

                        }

                        override fun onFailure(call: Call<ApiResponseIngredient>, t: Throwable) {
                            Log.e("Failed", "Api Failed" + t.message)
                        }
                    })


                    if(productList[0].categoryId == 1){
                        sizeButtonSmall.setOnClickListener {
                            size.text =  "Маленькая 20см "
                            type.text = "традиционное тесто"
                            weight.text = " 350г"

                            newTotalPrice = (totalPrice.minus((onePercent?.times(15)!!)))
                            totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"


                            typeButtonTraditional.setOnClickListener {
                                type.text = "традиционное тесто"
                                weight.text = " 350г"

                                newTotalPrice = (totalPrice.minus((onePercent.times(15))))
                                totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"


                            }
                            typeButtonSlim.setOnClickListener {
                                type.text = "тонкое тесто"
                                weight.text = " 300г"

                                newTotalPrice = (totalPrice.minus((onePercent.times(20))))
                                totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"


                            }
                        }
                        sizeButtonMedium.setOnClickListener {
                            size.text = "Средняя 30см "
                            type.text = "традиционное тесто"
                            weight.text = " 490г"
                            totalPriceButton.text = "В КОРЗИНУ ЗА ${productList[0].price} ₽"
                            typeButtonTraditional.setOnClickListener {

                                type.text = "традиционное тесто"
                                weight.text = " 490г"
                                totalPriceButton.text = "В КОРЗИНУ ЗА ${productList[0].price} ₽"
                            }

                            typeButtonSlim.setOnClickListener {
                                type.text = "тонкое тесто"
                                weight.text = " 400г"

                                newTotalPrice = (totalPrice?.minus((onePercent?.times(5)!!))!!)
                                totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"


                            }
                        }
                        sizeButtonBig.setOnClickListener {
                            size.text = "Большая 45см "
                            type.text = "традиционное тесто"
                            weight.text = " 800г"

                            newTotalPrice = (totalPrice?.plus((onePercent?.times(40)!!))!!)
                            totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"


                            typeButtonTraditional.setOnClickListener {
                                type.text = "традиционное тесто"
                                weight.text = " 800г"

                                newTotalPrice = (totalPrice?.plus((onePercent?.times(40)!!))!!)
                                totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"
                            }
                            typeButtonSlim.setOnClickListener {
                                type.text = "тонкое тесто"
                                weight.text = " 700г"

                                newTotalPrice = (totalPrice?.plus((onePercent?.times(20)!!))!!)
                                totalPriceButton.text = "В КОРЗИНУ ЗА $newTotalPrice ₽"
                            }
                        }
                        typeButtonTraditional.setOnClickListener {
                            Toast.makeText(applicationContext,"Сначала выберете размер",Toast.LENGTH_SHORT).show()
                        }

                        typeButtonSlim.setOnClickListener {
                            Toast.makeText(applicationContext,"Сначала выберете размер",Toast.LENGTH_SHORT).show()
                        }
                    }


                    /* Если категория товара != пицце то не показываем эти элементы на экране */

                    if (productList[0].categoryId != 1 ){
                        sizeButtonsContainer.visibility = View.GONE
                        typeButtonsContainer.visibility = View.GONE
                        size.visibility = View.GONE
                        type.visibility = View.GONE
                        weight.visibility = View.GONE
                        addPizzaText.visibility = View.GONE
                        ingredientsRecycle.visibility = View.GONE

                    }




                    totalPriceButton.setOnClickListener {
                        val intent = Intent(this@ProductActivity, BasketActivity::class.java)
                        intent.putExtra("productId",productList[0].idProduct.toString())
                        startActivity(intent)
                    }


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