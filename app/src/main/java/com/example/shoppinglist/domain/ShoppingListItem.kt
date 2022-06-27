package com.example.shoppinglist.domain

data class ShoppingListItem(
    val name:String,
    val count:Int,
    val enabled:Boolean,
    var id:Int = UNDEFINDED_ID,
){
    companion object{
        const val UNDEFINDED_ID = -1
    }
}
