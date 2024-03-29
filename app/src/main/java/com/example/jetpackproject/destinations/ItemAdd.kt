package com.example.jetpackproject.destinations

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackproject.AllDestinations
import com.example.jetpackproject.R
import com.example.jetpackproject.db.DBItem
import com.example.jetpackproject.db.DBItemViewModel
import com.example.jetpackproject.db.humanoids

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemAdd(item: DBItem?, viewModel: DBItemViewModel) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var itemId by rememberSaveable { mutableIntStateOf(item?.id ?: -1) }
    var itemType by rememberSaveable { mutableStateOf(item?.item_type ?: humanoids[0]) }
    var textName by rememberSaveable { mutableStateOf(item?.text_name ?: "") }
    var textSpec by rememberSaveable { mutableStateOf(item?.text_spec ?: "") }
    var dangerous by rememberSaveable { mutableStateOf(item?.dangerous ?: false) }
    var itemStrength by rememberSaveable { mutableFloatStateOf(item?.item_strength ?: 0.0f) }

    LaunchedEffect(item) {
        if (item != null && itemId != item.id) {
            itemId = item.id
            itemType = item.item_type
            textName = item.text_name
            textSpec = item.text_spec
            dangerous = item.dangerous
            itemStrength = item.item_strength
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Text(
            text = if (item !=null) {
                "Modify item"
            } else {
                "Add new item"
            },
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(text = "Name")
        TextField(
            value = textName,
            placeholder = { Text(text = "Name") },
            onValueChange = { textName = it },
            modifier = Modifier
                .fillMaxWidth()
            //.scale(scaleY = 0.9F, scaleX = 1F)
        )
        //Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Specific info")
        TextField(
            value = textSpec,
            placeholder = { Text(text = "Here add specific info") },
            onValueChange = { textSpec = it },
            modifier = Modifier
                .fillMaxWidth()
            //.scale(scaleY = 0.9F, scaleX = 1F)
        )
        //Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Strength")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Slider(
                value = itemStrength,
                onValueChange = { itemStrength = it },
                valueRange = 0f..5f,
                steps = 9,
                modifier = Modifier.weight(4f)
            )
            Text(
                text = String.format("%.1f", itemStrength),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Row (
            //modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = dangerous,
                onCheckedChange = { dangerous = it },
                modifier = Modifier.weight(1.0F))
            Text(text = "Is dangerous?",
                modifier = Modifier
                .weight(2.0F),
                textAlign = TextAlign.Left)
            Column(
                modifier = Modifier.weight(2.0F)
            ) {
                humanoids.forEach { type ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        ) {
                        RadioButton(
                            selected = itemType == type,
                            onClick = { itemType = type },

                        )
                        Text(
                            text = type,

                        )
                    }

                }
            }
        }



            // save/cancel buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        val resultItem = DBItem(
                            item?.id ?: 0,
                            if(textName!=""){textName}else{"Default name"},
                            textSpec,
                            itemStrength,
                            itemType,
                            dangerous
                        )
                        if (item == null) {
                            viewModel.insertData(resultItem)
                        } else {
                            viewModel.updateData(resultItem)
                        }

                        onBackPressedDispatcher?.onBackPressed()
                    },
                    //modifier = Modifier.weight(1.0F)
                ) {
                    Text(text = "Save")
                }
                Button(
                    onClick = { onBackPressedDispatcher?.onBackPressed() },
                    //modifier = Modifier.weight(1.0F)
                ) {
                    Text(text = "Cancel")
                }
            }
        }



}