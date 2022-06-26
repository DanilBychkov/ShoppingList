package com.example.shoppinglist.domain

class EditShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopList(shopItem: ShoppingListItem){
        shopListRepository.editShopList(shopItem)
    }
}