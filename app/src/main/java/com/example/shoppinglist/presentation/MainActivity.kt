package com.example.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            shopListAdapter.submitList(it)
        }
        val buttonAddSI=findViewById<ImageButton>(R.id.buttonAddShopItem)
        buttonAddSI.setOnClickListener{
            val intent= ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.recyclerViewId)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setLongClickListener()
        setShortClickListener()
        setUpSwipeListener(rvShopList)
    }

    private fun setLongClickListener(){
        shopListAdapter.onShopItemLongClickListener={
            viewModel.editShopListUseCase(it)
        }
    }

    private fun setShortClickListener(){
        shopListAdapter.onShopItemShortClickListener={
            val intent= ShopItemActivity.newIntentEditItem(this,it.id)
            startActivity(intent)
            Toast.makeText(this, it.id.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpSwipeListener(rvShopListener:RecyclerView){
        val callback=object :ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item =shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopList(item)
            }
        }
        val itemTouchHelper =ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopListener)
    }
}