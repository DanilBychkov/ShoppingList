package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.ShoppingListItem

class MainViewModel:ViewModel() {

    private val repository= ShopListRepositoryImpl

    private val getShopListUseCase= GetShopListUseCase(repository)
    private val deleteShopItemUseCase =DeleteShopItemUseCase(repository)
    private val editShopItemUseCase= EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopList(shopItem:ShoppingListItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopListUseCase(shopItem:ShoppingListItem){
        val newItem=shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopList(newItem)
    }
}