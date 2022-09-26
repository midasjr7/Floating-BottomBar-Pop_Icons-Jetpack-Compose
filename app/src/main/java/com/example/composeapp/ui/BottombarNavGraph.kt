package com.example.composeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeapp.ui.Screens.HomeScreen
import com.example.composeapp.ui.Screens.ProfileScreen
import com.example.composeapp.ui.Screens.SearchScreen
import com.example.composeapp.ui.Screens.SettingsScreen
import com.example.composeapp.ui.theme.Screen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route)
        {
            HomeScreen()
        }
        composable(route = Screen.Search.route)
        {
            SearchScreen()
        }
        composable(route = Screen.Profile.route)
        {
            ProfileScreen()
        }
        composable(route = Screen.Settings.route)
        {
            SettingsScreen()
        }
    }
}