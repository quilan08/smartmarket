package com.example.e_commerce_gh.presentation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItems(var route : String, var icon : ImageVector ) {
    Home("Home", Icons.Default.Home),
    Details("Details", Icons.Default.ShoppingCart),
    Options("Markets", Icons.Default.Build),
    Settings("Profile", Icons.Default.Settings),
}