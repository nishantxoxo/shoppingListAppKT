package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class shoppingItem(val id:Int, var name:String, var quantity:Int, var isediting:Boolean)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun shoppingListApp(){
    var stiems by remember { mutableStateOf(listOf<shoppingItem>()) }
    var showdialog by remember { mutableStateOf(false) }




    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Button(onClick = { showdialog=true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ){
            items(stiems){}
        }
    }

    if(showdialog){
        AlertDialog(onDismissRequest = {showdialog = false}, confirmButton = {}, title = { Text("Add shoppin items") }, text = { })
    }
}