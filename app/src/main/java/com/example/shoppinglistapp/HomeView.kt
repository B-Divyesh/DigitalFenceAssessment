package com.example.shoppinglistapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppinglistapp.data.shopList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: shopListViewModel
){
    Scaffold(
        topBar = {AppBarView(title= "Shopping List")},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) { padding ->
        val shoplist = viewModel.getAllShopList.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            items(shoplist.value, key={item -> item.id} ){
                    item ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if(it == SwipeToDismissBoxValue.EndToStart){
                            viewModel.deleteShopList(item)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection
                                == SwipeToDismissBoxValue.EndToStart) Color.Red else Color.Transparent
                            ,label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White)
                        }

                    },
                    enableDismissFromStartToEnd = false,
                    content = {
                        ShopListItem(
                            shopList = item,
                            onCheckedChange = { isChecked ->
                                viewModel.updateIsPurchased(item.copy(isPurchased = isChecked))
                            },
                            onIncrease = {
                                viewModel.updateShopList(item.copy(quantity = item.quantity + 1))
                            },
                            onDecrease = {
                                if (item.quantity > 1) {
                                    viewModel.updateShopList(item.copy(quantity = item.quantity - 1))
                                }
                            }
                        ) {
                            val id = item.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }

}


@Composable
fun ShopListItem(
    shopList: shopList,
    onCheckedChange: (Boolean) -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onClick: () -> Unit
){
    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    val dateString = sdf.format(Date(shopList.createdAt))

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(text = shopList.name, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Quantity: ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    IconButton(onClick = onDecrease) {
                        Icon(imageVector = Icons.Default.Remove, contentDescription = "")
                    }
                    Text(
                        text = shopList.quantity.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = onIncrease) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
                Text(text = "Added on: $dateString", fontSize = 12.sp, color = Color.Gray)
            }
            Checkbox(
                checked = shopList.isPurchased,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
