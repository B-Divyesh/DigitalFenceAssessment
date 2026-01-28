package com.example.shoppinglistapp

import com.example.shoppinglistapp.data.ShopListDatabase
import com.example.shoppinglistapp.data.shopListDao
import com.example.shoppinglistapp.data.shopListRepository
import androidx.room.Room

import android.content.Context



object Graph {
    lateinit var database: ShopListDatabase

    val shopListRepository by lazy { shopListRepository(shopListDao = database.shopListDao()) }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, ShopListDatabase::class.java, "shoplist.db").build()
    }
}