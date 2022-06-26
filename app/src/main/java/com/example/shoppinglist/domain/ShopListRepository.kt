package com.example.shoppinglist.domain

interface ShopListRepository {

    fun addShopList(shopItem:ShoppingListItem)
    fun deleteShopList(shopItem:ShoppingListItem)
    fun editShopList(shopItem: ShoppingListItem)
    fun getItem(id: Int):ShoppingListItem
    fun getShopList():List<ShoppingListItem>

}