package com.example.shoppinglist.domain

class DeleteShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopList(shopItem:ShoppingListItem){
        shopListRepository.deleteShopList(shopItem)
    }
}