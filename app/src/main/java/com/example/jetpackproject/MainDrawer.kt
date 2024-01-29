package com.example.jetpackproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    route: String,
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit = {},
    navigateToImageSwipe: () -> Unit = {},
    navigateToItemList: () -> Unit = {},
    closeDrawer: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier) {
        //DrawerHeader(modifier)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(15.dp)
                .fillMaxWidth()
        ) {

            Image(
                painterResource(id = R.drawable.human_title),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        //Navigation Items
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.start_screen_desc),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route == AllDestinations.START_SCREEN,
            onClick = {
                navigateToMainScreen()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.image_swipe_desc),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route == AllDestinations.IMAGE_SWIPE,
            onClick = {
                navigateToImageSwipe()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )

        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.item_list_desc),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route == AllDestinations.ITEM_LIST,
            onClick = {
                navigateToItemList()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = null) },
            shape = MaterialTheme.shapes.small
        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier) {

}