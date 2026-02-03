package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppinglistapp.data.shopList
import kotlinx.coroutines.launch
import android.widget.Toast

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: shopListViewModel,
    navController: NavController
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    if (id != 0L){
        val item = viewModel.getAShopListById(id).collectAsState(initial = shopList(createdAt = 0L))
        item.value?.let {
            viewModel.shopListNameState = it.name
            viewModel.shopListQuantityState = it.quantity.toString()
        }
    } else {
        viewModel.shopListNameState = ""
        viewModel.shopListQuantityState = "1"
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if(id != 0L) "Update Item" else "Add Item"
            ) { navController.navigateUp() }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            ShopTextField(label = "Item Name",
                value = viewModel.shopListNameState,
                onValueChanged = {
                    viewModel.onShopListNameChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            ShopTextField(label = "Quantity",
                value = viewModel.shopListQuantityState,
                onValueChanged = {
                    viewModel.onShopListQuantityChanged(it)
                },
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick={
                if(viewModel.shopListNameState.isNotEmpty() &&
                    viewModel.shopListQuantityState.isNotEmpty()){
                    val quantity = viewModel.shopListQuantityState.toIntOrNull() ?: 1
                    if(id != 0L){
                        viewModel.updateShopList(
                            shopList(
                                id = id,
                                name = viewModel.shopListNameState.trim(),
                                quantity = quantity,
                                createdAt = System.currentTimeMillis() // Or keep original if you prefer
                            )
                        )
                    }else{
                        viewModel.addShopList(
                            shopList(
                                name = viewModel.shopListNameState.trim(),
                                quantity = quantity,
                                createdAt = System.currentTimeMillis()
                            )
                        )
                    }
                    scope.launch {
                        navController.navigateUp()
                    }
                } else {
                    if(viewModel.shopListNameState.isEmpty()) {
                        Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()

                    }
                }


            }){
                Text(
                    text = if (id != 0L) "Update Item" else "Add Item",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}


@Composable
fun ShopTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true
    )
}
