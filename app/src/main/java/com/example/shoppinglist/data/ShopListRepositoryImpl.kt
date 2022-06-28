package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopListRepository
import com.example.shoppinglist.domain.ShoppingListItem

object ShopListRepositoryImpl:ShopListRepository {
    private val shopListLD=MutableLiveData<List<ShoppingListItem>>()
    private val shopList = sortedSetOf<ShoppingListItem>({o1,o2 ->o1.id.compareTo(o2.id)})
    private var autoIncrementId=0

    init{
        for (i in 1..10){
            val item =ShoppingListItem("Name $i",i,true)
            shopList.add(item)
        }

    }

    override fun addShopList(shopItem: ShoppingListItem) {
        if(shopItem.id == ShoppingListItem.UNDEFINDED_ID){
            shopItem.id= autoIncrementId++
        }
        shopList.add(shopItem)
        updateLD()
    }

    override fun deleteShopList(shopItem: ShoppingListItem) {
        shopList.remove(shopItem)
        updateLD()
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

    override fun getShopList(): LiveData<List<ShoppingListItem>> {
        return shopListLD
    }

    private fun updateLD(){
        shopListLD.value= shopList.toList()
    }
}