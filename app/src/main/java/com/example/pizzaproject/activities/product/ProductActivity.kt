package com.example.pizzaproject.activities.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
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
import com.squareup.picasso.Picasso
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


                    val addPizzaText : TextView = findViewById(R.id.AddPizzaText)
                    val pizzaSize : TextView = findViewById(R.id.pizzaSize)



                    val responseBody = response.body()!!
                    productList = responseBody.data
                    Picasso.get().load( "http:/172.30.44.151:8090/images/product/"+productList[0].image).into(binding.pizzaImage)
                    binding.pizzaTitle.text=productList[0].nameProduct
                    binding.pizzaDescription.text = productList[0].description

                    if(productList[0].categoryId == 1){

                        pizzaSize.text = "Выберете размер для вашей пиццы."


                    }

                    if (productList[0].categoryId != 1 ){
                        sizeButtonsContainer.visibility = View.GONE
                        typeButtonsContainer.visibility = View.GONE
                        addPizzaText.visibility = View.GONE
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