package com.example.pizzaproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.model.ingredient.Ingredient
import com.squareup.picasso.Picasso

class IngredientsAdapter(
    private val list: List<Ingredient>,
    private val onClick:(id: Int?) -> Unit
) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

       val ingredientImage : ImageView = itemView.findViewById(R.id.ingredientImage)
        val priceButton : Button = itemView.findViewById(R.id.priceButton)

        init {
            priceButton.setOnClickListener {
                onClick(list[adapterPosition].ingredientId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
        val ingredient = list[position]
        holder.priceButton.text = list[position].ingredientCost.toString() + "₽"
        Picasso.get().load( "http:/172.30.44.151:8090/images/product/"+ingredient.ingredientPhoto).into(holder.ingredientImage)
    }


    override fun getItemCount(): Int {
        return list.size
    }


}