package com.example.jetpackproject.destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackproject.R

@Composable
fun StartScreen(imageId: Int) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 20.dp)
    ) {
        Text(
            text = "Main screen",
            fontSize = 22.sp,
            //style = MaterialTheme.typography.titleLarge
        )
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null
        )
        Text(
            text = "Jakub Cebula",
            fontSize = 14.sp
        )
    }
}