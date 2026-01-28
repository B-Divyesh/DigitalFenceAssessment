package com.example.shoppinglistapp.data

import kotlinx.coroutines.flow.Flow

class shopListRepository(private val shopListDao: shopListDao) {

    fun getAllLists(): Flow<List<shopList>> = shopListDao.getAllLists()

    fun getShopListById(id: Long): Flow<shopList> = shopListDao.getListById(id)

    suspend fun updateIsPurchased(shopList: shopList) {
        shopListDao.updateIsPurchased(shopList)
    }

    suspend fun addShopList(shopList: shopList) {
        shopListDao.addShopList(shopList)
    }

    suspend fun deleteShopList(shopList: shopList) {
        shopListDao.deleteShopList(shopList)
    }

}