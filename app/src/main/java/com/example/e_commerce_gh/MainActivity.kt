package com.example.e_commerce_gh

import PhoneAuthScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce_gh.presentation.bottombar.BottomNavControls
import com.example.e_commerce_gh.presentation.bottombar.NavigationItems
import com.example.e_commerce_gh.presentation.screens.DetailsScreen
import com.example.e_commerce_gh.presentation.screens.HomeScreen
import com.example.e_commerce_gh.presentation.screens.OptionsScreen
import com.example.e_commerce_gh.presentation.screens.SettingsScreen

import com.example.e_commerce_gh.ui.theme.EcommerceghTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceghTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold(
                   // bottomBar = { BottomNavControls(navController = navController) }
                ){it
                    NavHost(navController = navController, startDestination = "auth") {
                        composable("auth") { PhoneAuthScreen(navController = navController) }
                        composable("home") { HomeScreen() }
                    }

                }
            }
        }
    }
}

@Composable
fun NavigationRouter(navHostController: NavHostController){
    NavHost(navHostController, startDestination = NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) { HomeScreen() }
        composable(NavigationItems.Details.route) { DetailsScreen() }
        composable(NavigationItems.Options.route) { OptionsScreen() }
        composable(NavigationItems.Settings.route) { SettingsScreen() }
    }
}


