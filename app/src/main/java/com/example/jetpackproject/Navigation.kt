package com.example.jetpackproject

import androidx.navigation.NavHostController
import com.example.jetpackproject.AllDestinations.START_SCREEN
import com.example.jetpackproject.AllDestinations.IMAGE_SWIPE
import com.example.jetpackproject.AllDestinations.ITEM_LIST

object AllDestinations {
    const val START_SCREEN = "StartScreen"
    const val IMAGE_SWIPE = "ImageSwipe"
    const val ITEM_LIST = "ItemList"
    const val ITEM_DETAILS = "ItemDetails"
    const val ITEM_ADD = "ItemAdd"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(START_SCREEN) {
            launchSingleTop = true
            popUpTo(START_SCREEN)
        }
    }

    fun navigateToImages() {
        navController.navigate(IMAGE_SWIPE) {
            launchSingleTop = true
            popUpTo(START_SCREEN)
        }
    }

    fun navigateToItemsList() {
        navController.navigate(ITEM_LIST) {
            launchSingleTop = true
            popUpTo(START_SCREEN)
        }
    }
}