package com.example.shoppinglistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "list_table")
data class shopList(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo("item_name")
    val name: String = "",
    @ColumnInfo("item_quantity")
    val quantity: Int = 1,
    @ColumnInfo("item_is_purchased")
    val isPurchased: Boolean = false,
    @ColumnInfo("item_created_at")
    val createdAt: Long
)