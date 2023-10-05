package com.example.pizzaproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.model.category.Category

class CategoriesAdapter(
    private  val list: List<Category>,
    private val onClick: (id: Int) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>()
 {
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
         val categoryButton: Button = ItemView.findViewById(R.id.categoryButton)

         init {
             categoryButton.setOnClickListener{
                 onClick(list[adapterPosition].id_category)
             }
         }

     }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list,parent,false)
         return ViewHolder(view)
     }
     override fun getItemCount(): Int {
                return  list.size
     }
     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val category = list[position]
         holder.categoryButton.text = category.category


     }
 }