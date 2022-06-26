package com.example.shoppinglist.domain

class AddShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopList(shopItem:ShoppingListItem){
        shopListRepository.addShopList(shopItem)
    }
}