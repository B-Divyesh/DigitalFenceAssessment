package com.example.shoppinglistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglistapp.data.shopList
import com.example.shoppinglistapp.data.shopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class shopListViewModel(
    private val shopListRepository: shopListRepository = Graph.shopListRepository
) : ViewModel() {
    var shopListNameState by mutableStateOf("")
    var shopListQuantityState by mutableStateOf("1")

    fun onShopListNameChanged(newString: String) {
        shopListNameState = newString
    }

    fun onShopListQuantityChanged(newString: String) {
        shopListQuantityState = newString
    }

    lateinit var getAllShopList: Flow<List<shopList>>

    init {
        getAllShopList = shopListRepository.getAllLists()
    }

    fun addShopList(shopList: shopList) {
        viewModelScope.launch(Dispatchers.IO) {
            shopListRepository.addShopList(shopList = shopList)
        }
    }

    fun getAShopListById(id: Long): Flow<shopList> {
        return shopListRepository.getShopListById(id)
    }

    fun updateShopList(shopList: shopList) {
        viewModelScope.launch(Dispatchers.IO) {
            shopListRepository.addShopList(shopList) // Using addShopList as it uses REPLACE conflict strategy
        }
    }

    fun updateIsPurchased(shopList: shopList) {
        viewModelScope.launch(Dispatchers.IO) {
            shopListRepository.updateIsPurchased(shopList = shopList)
        }
    }

    fun deleteShopList(shopList: shopList) {
        viewModelScope.launch(Dispatchers.IO) {
            shopListRepository.deleteShopList(shopList = shopList)
        }
    }

}
