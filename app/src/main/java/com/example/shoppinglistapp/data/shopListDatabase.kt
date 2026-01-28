package com.example.shoppinglistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [shopList::class],
    version = 1,
    exportSchema = false

)
abstract class ShopListDatabase : RoomDatabase() {
    abstract fun shopListDao() : shopListDao
}