package com.example.shoppinglist.domain

class GetItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getItem(id: Int):ShoppingListItem{
       return shopListRepository.getItem(id)
    }
}