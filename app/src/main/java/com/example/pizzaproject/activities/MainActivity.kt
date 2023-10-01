package com.example.pizzaproject.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.adapters.CategoriesAdapter
import com.example.pizzaproject.model.ApiService
import com.example.pizzaproject.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    private val apiService = ApiService.getService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<Category>()
        val adapter = CategoriesAdapter(list){

            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(EXTRA_ID, it)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO){
            val categories = apiService.getCategories()
            list.addAll(categories)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }

        }

    }
}