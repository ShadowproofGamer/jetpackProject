package com.example.jetpackproject.destinations

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.jetpackproject.AllDestinations
import com.example.jetpackproject.R
import com.example.jetpackproject.db.DBItem
import com.example.jetpackproject.db.DBItemViewModel
import com.example.jetpackproject.db.humanoids


@Composable
fun ItemDetails(
    itemId: Int,
    viewModel: DBItemViewModel,
    navController: NavController
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val item = viewModel.getData(itemId).collectAsState(initial = DBItem()).value
    val icon = when (item.item_type) {
        humanoids[0] -> R.drawable.human
        humanoids[1] -> R.drawable.npc
        humanoids[2] -> R.drawable.enemy
        else -> {
            R.drawable.npc
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 60.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = item.text_name,
            fontSize = 20.sp,
            color = Color(0xFFFFA500),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.text_spec, fontSize = 15.sp
        )


        Spacer(modifier = Modifier.padding(15.dp))
        Column {
            Text(text = "Rating")
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = (item.item_strength / 5.0f),
                    modifier = Modifier.weight(4f)
                )
                Text(
                    text = String.format("%.1f", item.item_strength),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = item.dangerous,
                onCheckedChange = null,
                enabled = false
            )
            Text(
                text = "Is dangerous",
                modifier = Modifier
                    .weight(1.0F)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Left
            )
            //Text(text = "${item.item_strength}", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        }
        Spacer(modifier = Modifier.padding(15.dp))


        //buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = {
                navController.navigate("${AllDestinations.ITEM_ADD}/${item.id}")
            }) {
                Text(text = "Modify")
            }
            Button(onClick = {
                onBackPressedDispatcher?.onBackPressed()
            }) {
                Text(text = "Back")
            }
        }
    }

}