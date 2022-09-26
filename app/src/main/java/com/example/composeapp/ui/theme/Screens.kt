package com.example.composeapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id:String,
    val title:String,
    val route:String,
    val icon: ImageVector,
    val icon_focused: ImageVector,
){



    object Home:Screen("home_01","Home","Home", Icons.Outlined.Home,Icons.Default.Home)
    object Search:Screen("search_02","Search","Search",Icons.Outlined.Search,Icons.Default.Search)
    object Profile:Screen("profile_03","Profile","Profile",Icons.Outlined.Person,Icons.Default.Person)
    object Settings:Screen("settings_04","Settings","Settings",Icons.Outlined.Settings,Icons.Default.Settings)

    object Items{
        val list= listOf(
            Home,Search,Profile,Settings
        )
    }

}