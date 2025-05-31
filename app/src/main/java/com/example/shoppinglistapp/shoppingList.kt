package com.example.shoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class shoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isediting: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun shoppingListApp() {
    var stiems by remember { mutableStateOf(listOf<shoppingItem>()) }
    var showdialog by remember { mutableStateOf(false) }
    var itemname by remember { mutableStateOf("") }
    var itemquant by remember { mutableStateOf(0) }



    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Button(
            onClick = { showdialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(stiems) {
                shoppingListItems(it, {}, {})
            }
        }
    }

    if (showdialog) {
        AlertDialog(onDismissRequest = { showdialog = false }, confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if (itemname.isNotBlank()) {
                        val newitem = shoppingItem(
                            id = stiems.size + 1,
                            name = itemname,
                            quantity = itemquant ?: 1
                        )
                        stiems = stiems + newitem
                        showdialog = false
                        itemname = ""

                    }
                }) {

                    Text("add")
                }
                Button(onClick = { showdialog = false }) {
                    Text("cancel")
                }
            }

        }, title = { Text("Add shoppin items") }, text = {
            Column {
                OutlinedTextField(
                    value = itemname,
                    onValueChange = { itemname = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = itemquant.toString(),
                    onValueChange = { itemquant = it.toInt() },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }


        })
    }
}

@Composable
fun shoppingItemEditor(item: shoppingItem, onEditComp: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuant by remember { mutableStateOf(item.quantity.toString()) }
    var isediting by remember { mutableStateOf(item.isediting) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column() {
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuant,
                onValueChange = { editedQuant = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }
        Button(onClick = {
            isediting = false;
            onEditComp(editedName, editedQuant.toIntOrNull()?: 1 , )
        }) { Text("save") }

    }


}


@Composable
fun shoppingListItems(item: shoppingItem, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(border = BorderStroke(2.dp, Color.Cyan))
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "QTY: ${item.quantity}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = ""
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}
