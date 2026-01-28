package com.example.shoppinglistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface shopListDao {
    @Query("select * from `list_table`" )
    fun getAllLists(): Flow<List<shopList>>

    @Query("select * from `list_table` where id=:id" )
    fun getListById(id: Long): Flow<shopList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopList(shopListEntity: shopList)

    @Update
    suspend fun updateIsPurchased(shopListEntity: shopList)

    @Delete
    suspend fun deleteShopList(shopListEntity: shopList)

}