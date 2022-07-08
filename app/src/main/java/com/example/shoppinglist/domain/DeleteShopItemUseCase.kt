package com.example.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem:ShoppingListItem){
        shopListRepository.deleteShopList(shopItem)
    }
}