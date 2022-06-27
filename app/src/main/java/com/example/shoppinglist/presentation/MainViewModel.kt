package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopListUseCase
import com.example.shoppinglist.domain.EditShopListUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.ShoppingListItem

class MainViewModel:ViewModel() {

    private val repository= ShopListRepositoryImpl

    private val getShopListUseCase= GetShopListUseCase(repository)
    private val deleteShopListUseCase =DeleteShopListUseCase(repository)
    private val editShopListUseCase= EditShopListUseCase(repository)

    val shopList = MutableLiveData<List<ShoppingListItem>>()

    fun getShopList(){
        val list =getShopListUseCase.getShopList()
        shopList.value=list
    }

    fun deleteShopList(shopItem:ShoppingListItem){
        deleteShopListUseCase.deleteShopList(shopItem)
        getShopList()
    }

    fun editShopListUseCase(shopItem:ShoppingListItem){
        val newItem=shopItem.copy(enabled = !shopItem.enabled)
        editShopListUseCase.editShopList(newItem)
        getShopList()
    }
}