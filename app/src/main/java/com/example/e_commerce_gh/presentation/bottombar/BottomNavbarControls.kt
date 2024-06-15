package com.example.e_commerce_gh.presentation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun BottomNavControls(
        navController : NavHostController
){
        val items = listOf(
                NavigationItems.Home,
                NavigationItems.Details,
                NavigationItems.Options
                ,NavigationItems.Settings
        )

        var selectedItems by rememberSaveable {
                mutableStateOf(0)
        }

        NavigationBar{
                items.forEachIndexed { index, navigationItems ->

                        NavigationBarItem(
                                alwaysShowLabel = true,
                                selected = selectedItems == index,
                                onClick = { selectedItems = index
                                          navController.navigate(navigationItems.route){
                                                  navController.graph.startDestinationRoute?.let { route->
                                                          popUpTo(route){
                                                                  saveState = true
                                                          }
                                                  }
                                                  launchSingleTop = true
                                                  restoreState = true
                                          }
                                          },
                                label = { Text(navigationItems.name) },
                                icon = { Icon(imageVector = navigationItems.icon, contentDescription =null ) }
                        )
                }
        }

}