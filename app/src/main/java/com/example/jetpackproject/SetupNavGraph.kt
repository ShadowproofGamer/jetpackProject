package com.example.jetpackproject

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.jetpackproject.db.DBItemViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackproject.db.DBItem
import com.example.jetpackproject.destinations.ImageSwipe
import com.example.jetpackproject.destinations.ItemAdd
import com.example.jetpackproject.destinations.ItemDetails
import com.example.jetpackproject.destinations.ItemList
import com.example.jetpackproject.destinations.StartScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun SetupNavGraph(
    navController: NavHostController, padding: PaddingValues, viewModel: DBItemViewModel
) {
    var selectedImage by rememberSaveable { mutableIntStateOf(R.drawable.human_title) }
    NavHost(
        navController = navController,
        startDestination = AllDestinations.START_SCREEN,
        modifier = Modifier.padding(padding)
    ) {
        //default start screen
        composable(route = AllDestinations.START_SCREEN) {
            StartScreen(imageId = selectedImage)
        }

        //start screen after image swipe
        composable(route = "${AllDestinations.START_SCREEN}/{img}",
            arguments = listOf(navArgument(name = "img") {
                type = NavType.StringType
            })) { navBackStackEntry ->
            val image = navBackStackEntry.arguments?.getString("img")
            if (image != null) {
                selectedImage = image.toInt()
            }
            StartScreen(imageId = selectedImage)
        }

        //image swipe
        composable(route = AllDestinations.IMAGE_SWIPE) {
            ImageSwipe(navController = navController)
        }

        //item list
        composable(route = AllDestinations.ITEM_LIST) {
            ItemList(navController = navController, viewModel = viewModel)
        }


        composable(route = "${AllDestinations.ITEM_DETAILS}/{itemId}",
            arguments = listOf(navArgument(name = "itemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")?.toInt()
            if (itemId != null) {
                ItemDetails(itemId = itemId, viewModel = viewModel, navController = navController)
            }
        }

        composable(route = AllDestinations.ITEM_ADD) {
            ItemAdd(item = null, viewModel = viewModel, navController = navController)
        }

        composable(route = "${AllDestinations.ITEM_ADD}/{itemId}",
            arguments = listOf(navArgument(name = "itemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString("itemId")?.toInt()
            val item = viewModel.getData(itemId!!)
                .collectAsStateWithLifecycle(initialValue = DBItem()).value

            ItemAdd(item = item, viewModel = viewModel, navController = navController)
        }
    }
}


