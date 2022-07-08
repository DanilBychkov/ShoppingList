package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*
import java.lang.Exception

class ShopItemViewModel: ViewModel() {
    private val repository= ShopListRepositoryImpl

    private val getShopItemUseCase= GetItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase= EditShopItemUseCase(repository)

    private val _errorInputName= MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount=MutableLiveData<Boolean>()
    val errorInputCount:LiveData<Boolean>
        get()= _errorInputCount

    private val _shopItem=MutableLiveData<ShoppingListItem>()
    val shopItem:LiveData<ShoppingListItem>
        get()=_shopItem

    private val _shouldCloseScreen=MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
        get() = _shouldCloseScreen

    fun GetShopItem(id:Int){
        val item= getShopItemUseCase.getItem(id)
        _shopItem.value=item
    }

    fun AddShopItem(inputName:String?,inputCount:String?){
        val name =parseName(inputName)
        val count =parseCount(inputCount)
        val fieldsValid= ValidateInput(name,count)
        if(fieldsValid) {
            val shopItem= ShoppingListItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }

    fun EditShopItem(inputName:String?,inputCount:String?){
        val name =parseName(inputName)
        val count =parseCount(inputCount)
        val fieldsValid= ValidateInput(name,count)
        if(fieldsValid) {
            _shopItem.value?.let {
                val item= it.copy(name=name,count=count)
                editShopItemUseCase.editShopList(item)
                finishWork()
            }
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

     private fun ValidateInput(name:String, count: Int):Boolean{
        var isCorrect=true
        if(name.isBlank()){
            isCorrect=false
            _errorInputName.value=true
        }
        if(count<=0){
            isCorrect=false
            _errorInputCount.value=true
        }
        return isCorrect
    }

    fun ResetInputErrorMessage(){
        _errorInputName.value=false
        _errorInputCount.value=false
    }

    private fun finishWork(){
        _shouldCloseScreen.value=Unit
    }
}