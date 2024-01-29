package com.example.jetpackproject.destinations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackproject.AllDestinations
import com.example.jetpackproject.R

val pagerImages: List<Int> = listOf(R.drawable.emotion_neutral, R.drawable.human_title, R.drawable.npc_title, R.drawable.enemy_title)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSwipe(
    navController: NavHostController,
    currentImage: Int = R.drawable.human_title
) {
    val pagerState = rememberPagerState(initialPage = pagerImages.indexOf(currentImage), pageCount = { 4 })
    //val pagerContent: List<Int> = listOf(R.drawable.emotion_neutral, R.drawable.human_title, R.drawable.npc_title, R.drawable.enemy_title)
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 20.dp)
    ) {
        Text(text = "Choose your image", fontSize = 25.sp)
        HorizontalPager(state = pagerState) { page ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(id = pagerImages[page]), contentDescription = "")
            }
        }
        Button(onClick = { navController.navigate(AllDestinations.START_SCREEN + "/${pagerImages[pagerState.currentPage]}") }) {
            Text(text = "Save")
        }
    }
}