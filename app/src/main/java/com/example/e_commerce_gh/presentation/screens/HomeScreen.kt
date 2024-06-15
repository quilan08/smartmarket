package com.example.e_commerce_gh.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(){

        val navigateController = rememberNavController()
        val tabs = listOf("Category","Category2","Category3" +
                "Category4")
        val selectedItem = remember {
            mutableIntStateOf(0)
        }
        Scaffold (
            topBar ={
                TabRow(selectedTabIndex =selectedItem.intValue ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(selected = selectedItem.intValue == index, onClick = {
                            selectedItem.intValue = index
                            if (index ==0){
                                navigateController.navigate("Category")
                            }else if (index ==1){
                                navigateController.navigate("Category3")
                            } else if (index ==2){
                                navigateController.navigate("Category2")
                            }else{
                                navigateController.navigate("Category4")
                            }
                        })
                    }
                }
            },
           
        ){it

        }


}




@Composable
fun SecondScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Second Screen")
    }
}