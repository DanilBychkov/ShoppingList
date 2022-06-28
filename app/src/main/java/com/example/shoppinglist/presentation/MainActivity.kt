package com.example.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.ShoppingListItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Toast.makeText(this, viewModel.shopList.value?.size.toString(), Toast.LENGTH_SHORT).show()
        viewModel.shopList.observe(this){
            adapter.shopList=it
        }
    }

    private fun setupRecyclerView(){
        val rvShopList= findViewById<RecyclerView>(R.id.recyclerViewId)
        adapter= ShopListAdapter()
        rvShopList.adapter=adapter

    }
}