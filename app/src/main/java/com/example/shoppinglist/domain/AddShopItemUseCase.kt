package com.example.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem:ShoppingListItem){
        shopListRepository.addShopList(shopItem)
    }
}