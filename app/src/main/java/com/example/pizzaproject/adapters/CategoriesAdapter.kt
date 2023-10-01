package com.example.pizzaproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.model.Category

class CategoriesAdapter(
    private  val list: List<Category>,
    private val onClick : (id : Int) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>()
 {


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val itemView = LayoutInflater.from(parent.context)
             .inflate(R.layout.category_list,parent,false)
         return ViewHolder(itemView)
     }

     override fun getItemCount(): Int {
                return  list.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         holder.categoryButton.text = list[position].category

     }

     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val categoryButton: Button = itemView.findViewById(R.id.categoryButton)
         init{
             itemView.setOnClickListener{
                 onClick(list[adapterPosition].category_id)
             }
         }
     }



 }