package com.example.jetpackproject.destinations

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackproject.R
import com.example.jetpackproject.db.DBItem
import com.example.jetpackproject.db.humanoids
import androidx.navigation.NavHostController
import com.example.jetpackproject.AllDestinations
import com.example.jetpackproject.db.DBItemViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemRow(
    item: DBItem,
    onClick: (item: DBItem) -> Unit,
    onLongClick: (item: DBItem) -> Unit
) {
    val icon = when (item.item_type) {
        humanoids[0] -> R.drawable.human
        humanoids[1] -> R.drawable.npc
        humanoids[2] -> R.drawable.enemy
        else -> {
            R.drawable.npc
        }
    }
    val openDialog = remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                onClick(item)
            },
                onLongClick = {
                openDialog.value = true
            })
            .padding(vertical = 10.dp, horizontal = 20.dp)) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "element icon",
            modifier = Modifier
                .padding(end = 10.dp)
                .size(50.dp)
        )
        Column {
            Text(
                text = item.text_name,
                fontSize = 20.sp,
                color = Color(0xFFFFA500),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.text_spec, fontSize = 15.sp
            )
        }
    }
    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Text(text = "Delete item")
        }, text = {
            Text(text = "Are you sure you want to delete?")
        }, confirmButton = {
            Button(onClick = {
                onLongClick(item)
                openDialog.value = false
            }) {
                Text(text = "Yes")
            }
        }, dismissButton = {
            Button(onClick = {
                openDialog.value = false
            }) {
                Text(text = "No")
            }
        })
    }

}

@Composable
fun ItemList(
    navController: NavHostController,
    viewModel: DBItemViewModel
) {
    val items = viewModel.dataList.collectAsState(initial = emptyList())
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn {
            itemsIndexed(
                items.value
            ) { index, item ->
                ItemRow(item = item,
                    onClick = {itemDB: DBItem ->
                        navController.navigate("${AllDestinations.ITEM_DETAILS}/${item.id}") {
                            popUpTo(AllDestinations.ITEM_LIST)
                            launchSingleTop = true
                        }
                    },
                    onLongClick = {
                            itemDB: DBItem -> viewModel.deleteData(itemDB)
                    }
                )

            }
        }
    }

}