package com.example.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopList(shopItem: ShoppingListItem){
        shopListRepository.editShopList(shopItem)
    }
}