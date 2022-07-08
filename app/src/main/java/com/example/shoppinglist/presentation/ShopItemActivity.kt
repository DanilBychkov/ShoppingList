package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShoppingListItem
import com.google.android.material.textfield.TextInputLayout


/*
private lateinit var viewModel: ShopItemViewModel

private lateinit var tilName:TextInputLayout
private lateinit var tilCount: TextInputLayout
private lateinit var etName:EditText
private lateinit var etCount:EditText
private lateinit var saveButton:Button

private var screenMode=ShopItemActivity.INPUT_MODE_UNKNOWN
private var shopItemId=ShoppingListItem.UNDEFINDED_ID
*/

class ShopItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        //parseIntent()
        //viewModel=ViewModelProvider(this)[ShopItemViewModel::class.java]
        //initViews()
        //addTextChangeListeners()
        //lunchRightMode()
    }
    /*
    private fun observeViewModel(){
        viewModel.errorInputCount.observe(this){
            val message = if(it){
                getString(R.string.error_input_count)
            }else{
                null
            }
            tilCount.error=message
        }
        viewModel.errorInputName.observe(this){
            val message = if(it){
                getString(R.string.error_input_name)
            }else{
                null
            }
            tilName.error=message
        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }

    private fun lunchRightMode(){
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        when(mode){
            MODE_EDIT->lunchEditMode()
            MODE_ADD->lunchAddMode()
        }
    }

    private fun lunchEditMode(){
        viewModel.GetShopItem(shopItemId)
        viewModel.shopItem.observe(this){
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        saveButton.setOnClickListener{
            viewModel.EditShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun lunchAddMode(){
        saveButton.setOnClickListener{
            viewModel.EditShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun addTextChangeListeners(){
        etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.ResetInputErrorMessage()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        etCount.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.ResetInputErrorMessage()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun finishActivity(){
        if(viewModel.shouldCloseScreen.value != null) {
            finishActivity(SUCSEFULL_FINISH)
        }
    }

    private fun parseIntent(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if(mode != MODE_EDIT && mode != MODE_ADD){
            throw RuntimeException("Unkown screen mode $mode")
        }
        screenMode= mode
        if(screenMode== MODE_EDIT){
            if(!intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
                throw RuntimeException("Param shop item id is abset")
            }
            shopItemId=intent.getIntExtra(EXTRA_SHOP_ITEM_ID,ShoppingListItem.UNDEFINDED_ID)
        }
    }

    private fun initViews(){
        tilName=findViewById(R.id.textInputLayout4)
        tilCount=findViewById(R.id.textInpLayCount)
        etCount=findViewById(R.id.editCount)
        etName=findViewById(R.id.editName)
        saveButton=findViewById(R.id.saveButton)
    }
    */
    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val SUCSEFULL_FINISH=1
        const val INPUT_MODE_UNKNOWN=""
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }

    }
}