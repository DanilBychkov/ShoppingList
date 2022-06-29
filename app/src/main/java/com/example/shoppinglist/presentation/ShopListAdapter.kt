package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingListItem

class ShopListAdapter:RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var shopList = listOf<ShoppingListItem>()
        set(value) {
            field=value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val tvName= view.findViewById<TextView>(R.id.name_object)
        val tvCount = view.findViewById<TextView>(R.id.count_object)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view_type = when(viewType){
            VIEW_TYPE_ENABLED->R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED->R.layout.item_shop_disabled
            else->throw RuntimeException("Unkown viewType")

        }
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context).inflate(view_type, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.tvName.text=shopItem.name
        holder.tvCount.text= shopItem.count.toString()
        holder.view.setOnClickListener { true }
    }

    override fun getItemViewType(position: Int): Int {
        return if(shopList[position].enabled){
            VIEW_TYPE_ENABLED
        }else{
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    companion object{
        const val VIEW_TYPE_ENABLED=0
        const val VIEW_TYPE_DISABLED=1
        const val MAX_POOL_SIZE=15
    }
}

