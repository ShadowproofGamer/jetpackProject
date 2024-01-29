package com.example.jetpackproject


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackproject.db.DBItemViewModelFactory
import com.example.jetpackproject.db.DBItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    factory: DBItemViewModelFactory,
    viewModel: DBItemViewModel = viewModel(factory = factory)
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.START_SCREEN
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }
    ModalNavigationDrawer(
        drawerContent = {
            MainDrawer(
                route = currentRoute,
                navigateToMainScreen = { navigationActions.navigateToStartScreen() },
                navigateToImageSwipe = { navigationActions.navigateToImageSwipe() },
                navigateToItemList = { navigationActions.navigateToItemList() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                modifier = Modifier
            )
        }, drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentRoute.substringBefore("/")
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    if (currentRoute.substringBefore("/")=="ItemList"){
                    IconButton(onClick = {
                        navController.navigate(AllDestinations.ITEM_ADD)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = null, tint = Color.Green
                        )
                    }
                } },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { drawerState.open() }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
            bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    content = {
                        IconButton(
                            onClick = {
                                navigationActions.navigateToImageSwipe()
                            }, modifier = Modifier.weight(1.0F)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.chevron_left),
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {
                                navigationActions.navigateToStartScreen()
                            }, modifier = Modifier.weight(1.0F)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.chevron_up),
                                contentDescription = null
                            )
                        }
                        IconButton(
                            onClick = {
                                navigationActions.navigateToItemList()
                            }, modifier = Modifier.weight(1.0F)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.chevron_right),
                                contentDescription = null
                            )
                        }

                    })
            }
        }, modifier = Modifier
        ) {
            SetupNavGraph(navController = navController, padding = it, viewModel = viewModel)
        }
    }
}
