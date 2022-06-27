package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.ShoppingListItem

object ShopListRepositoryImpl:ShopListRepository {

    private val shopList = mutableListOf<ShoppingListItem>()
    private var autoIncrementId=0

    override fun addShopList(shopItem: ShoppingListItem) {
        if(shopItem.id == ShoppingListItem.UNDEFINDED_ID){
            shopItem.id= autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopList(shopItem: ShoppingListItem) {
        shopList.remove(shopItem)
    }

    override fun editShopList(shopItem: ShoppingListItem) {
        val oldElement = getItem(shopItem.id)
        shopList.remove(oldElement)
        addShopList(shopItem)
    }

    override fun getItem(id: Int): ShoppingListItem {
        return shopList.find{
            it.id == id
        }?: throw RuntimeException("Element with id$id not found")
    }

    override fun getShopList(): List<ShoppingListItem> {
        return shopList.toList()
    }
}