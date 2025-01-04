package com.example.valoranttfg.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valoranttfg.composable.FullAgentsScreen
import com.example.valoranttfg.composable.FullMapsScreen
import com.example.valoranttfg.composable.FullWeaponsScreen
import com.example.valoranttfg.composable.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController= navController, startDestination = "Home_Screen"){
        composable("Home_Screen"){
            HomeScreen(navController)
        }
        composable("Agents_Screen"){
            FullAgentsScreen(navController)
        }
        composable("Weapons_Screen"){
            FullWeaponsScreen(navController)
        }
        composable("Maps_Screen"){
            FullMapsScreen(navController)
        }
    }
}