package com.example.pizzaproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaproject.R
import com.example.pizzaproject.model.product.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val list: List<Product>,
    private val onClick: (id: Int) -> Unit
): RecyclerView.Adapter<ProductsAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image : ImageView = itemView.findViewById(R.id.pizzaImage)
        val title : TextView = itemView.findViewById(R.id.pizzaTitle)
        val description : TextView = itemView.findViewById(R.id.pizzaDescription)
        val priceButton : Button = itemView.findViewById(R.id.priceAndInfoButton)
        init {
            priceButton.setOnClickListener{
                onClick(list[adapterPosition].idProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]
        holder.title.text = product.nameProduct
        holder.description.text = product.description
        holder.priceButton.text = "от " +  product.price.toString() + "р "
        Picasso.get().load( "http://188.234.244.32:8090/images/product/"+product.image).into(holder.image)


    }
    override fun getItemCount(): Int {
      return list.size
    }
}