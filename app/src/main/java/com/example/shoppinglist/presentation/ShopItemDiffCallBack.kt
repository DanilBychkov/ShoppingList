package com.example.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglist.domain.ShoppingListItem

class ShopItemDiffCallBack:DiffUtil.ItemCallback<ShoppingListItem>() {
    override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return  oldItem.id== newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return oldItem==newItem
    }
}