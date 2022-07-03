package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*
import java.lang.Exception

class ShopItemViewModel: ViewModel() {
    private val repository= ShopListRepositoryImpl

    private val getShopItemUseCase= GetItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase= EditShopItemUseCase(repository)

    fun GetShopItem(id:Int){
        val item= getShopItemUseCase.getItem(id)
    }

    fun AddShopItem(inputName:String?,inputCount:String?){
        val name =parseName(inputName)
        val count =parseCount(inputCount)
        val fieldsValid= validateInput(name,count)
        if(fieldsValid) {
            val shopItem= ShoppingListItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun EditShopItem(inputName:String?,inputCount:String?){
        val name =parseName(inputName)
        val count =parseCount(inputCount)
        val fieldsValid= validateInput(name,count)
        if(fieldsValid) {
            val shopItem =ShoppingListItem(name,count,true)
            editShopItemUseCase.editShopList(shopItem)
        }
    }

    private fun parseName(inputName:String?):String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?):Int{
        return try{
            inputCount?.trim()?.toInt()?:0
        } catch (e:Exception){
            0
        }
    }

    private fun validateInput(name:String,count: Int):Boolean{
        var isCorrect=true
        if(name.isBlank()){
            isCorrect=false
            //TODO:shop input error name
        }
        if(count<=0){
            isCorrect=false
            //TODO:shop input error count
        }
        return isCorrect
    }
}