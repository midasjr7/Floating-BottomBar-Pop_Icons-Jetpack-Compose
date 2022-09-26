package com.example.composeapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.ui.theme.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation() {
    val navController = rememberNavController()
    Surface(color = Color.Gray) {

        Scaffold(
            modifier = Modifier.padding(8.dp),
            bottomBar = {
                BottomNav(
                    navController
                )
            }
        ) {
            BottomNavGraph(navController = navController)
//            when(currentScreen){
//                Screen.Home -> HomeScreen()
//                Screen.Profile -> ProfileScreen()
//                Screen.Search -> SearchScreen()
//                Screen.Settings -> SettingsScreen()
//            }
        }

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNav(
    navController: NavHostController
) {

    val items = Screen.Items.list
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    Row(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(25.dp)
            )
            .padding(start = 8.dp)
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEach { item ->

            CustomBottomNavigationItem(
                item = item,
            currentDestination=currentDestination,
            navController = navController)

        }

    }

}

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(
    item: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val background =
        if (selected)
            MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        else Color.Transparent
    val contentColor = if (selected) MaterialTheme.colorScheme.primary
    else Color.Transparent

    Box(
        modifier = Modifier
            // .clip(CircleShape)
            .background(background)
            .fillMaxHeight()
            .padding(start = 8.dp)
            .clickable(onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            if (selected)
                Modifier.offset(y = (-12).dp)
            else Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = if(selected)Modifier
                    .background(
                        contentColor,
                        CircleShape
                    )
                    .size(38.dp)
                else
                    Modifier
                        .background(
                            contentColor,
                            CircleShape
                        )
                        .size(55.dp),
                contentAlignment = Center
            ) {
                Icon(
                   if(selected)item.icon_focused else item.icon,
                    null,
                    Modifier.size(20.dp),
                    tint = if (selected) Color.White else Color.Black
                )
            }

            AnimatedVisibility(selected) {
                Text(
                    item.title,
                    modifier = Modifier
                        .padding(start = 5.dp),
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
        }
    }

}


@Composable
@Preview
fun Prev1() {
    val navController = rememberNavController()
    BottomNav(navController)
}


//@Composable
//@Preview
//fun Display() {
//    //Spacer(modifier = Modifier.height(12.dp))
//     //NavBar5(list = list)
//}
//
//
//@Composable
//fun NavBar5(
//    list: List<NavItem>,
//    defaultSelectedIndex: Int = 0
//) {
//
//    Box(
//        Modifier
//            .fillMaxWidth()
//            .height(56.dp)
//            .background(Color.White, RoundedCornerShape(25.dp))
//            .padding(start = 10.dp),
//    ) {
//
//        var selectedIndex by remember {
//            mutableStateOf(defaultSelectedIndex)
//        }
//
//        Row(
//            verticalAlignment = CenterVertically,
//            modifier = Modifier
//                .fillMaxSize(),
//        ) {
//
//            list.forEachIndexed { index, nav ->
//
//                Box(
//                    Modifier
//                        .fillMaxHeight()
//                        .weight(1f)
//                        .clickable {
//                            selectedIndex = index
//                        },
//                    contentAlignment = Center
//                ) {
//
//                    Column(
//                        if (selectedIndex == index)
//                            Modifier.offset(y = (-12).dp)
//                        else Modifier,
//                        horizontalAlignment = CenterHorizontally,
//                    ) {
//                        Box(
//                            Modifier
//                                .background(
//                                    if (selectedIndex == index) MaterialTheme.colorScheme.primary
//                                    else Color.Transparent,
//                                    CircleShape
//                                )
//                                .size(36.dp),
//                            contentAlignment = Center
//                        ) {
//                            Icon(
//                                painterResource(nav.icon),
//                                null,
//                                Modifier.size(20.dp),
//                                tint = if (selectedIndex != index) Color.Gray else Color.White
//                            )
//                        }
//
//                        AnimatedVisibility(selectedIndex == index) {
//                            Text(
//                                nav.title,
//                                modifier = Modifier
//                                    .padding(top = 4.dp),
//                                color = Color.DarkGray,
//                                fontSize = 12.sp
//                            )
//                        }
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//}


//@Composable
//fun NavBar4(
//    list: List<NavItem>,
//    defaultSelectedIndex: Int = 0
//) {
//
//    Surface(
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        color = Color.White
//    ) {
//
//        var selectedIndex by remember {
//            mutableStateOf(defaultSelectedIndex)
//        }
//
//        Row(
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
//        ) {
//
//            list.forEachIndexed { index, nav ->
//
//                Box(
//                    Modifier
//                        .height(32.dp)
//                        .clickable {
//                            selectedIndex = index
//                        }
//                        .background(
//                            if (selectedIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent,
//                            RoundedCornerShape(4.dp)
//                        ),
//                    contentAlignment = Center
//                ) {
//
//                    Row(
//                        verticalAlignment = CenterVertically,
//                        modifier = Modifier.padding(4.dp)
//                    ) {
//
//                        Icon(
//                            painterResource(nav.icon),
//                            null,
//                            Modifier.size(24.dp),
//                            tint = if (selectedIndex != index) Color.Gray else Color.White
//                        )
//                        AnimatedVisibility(selectedIndex == index) {
//                            Text(
//                                nav.title,
//                                modifier = Modifier.padding(top = 4.dp),
//                                color = Color.White,
//                                fontSize = 12.sp
//                            )
//                        }
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//
//}
//@Composable
//fun NavBar2(
//    list: List<NavItem>,
//    defaultSelectedIndex: Int = 0
//) {
//
//    Surface(
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        color = Color.White
//    ) {
//
//        var selectedIndex by remember {
//            mutableStateOf(defaultSelectedIndex)
//        }
//
//        Row(Modifier.fillMaxSize()) {
//
//            list.forEachIndexed { index, icon ->
//
//                Box(
//                    Modifier
//                        .fillMaxHeight()
//                        .weight(1f),
//                    contentAlignment = Center
//                ) {
//
//                    Box(
//                        modifier = Modifier
//                            .size(40.dp)
//                            .clickable {
//                                selectedIndex = index
//                            }
//                            .background(
//                                if (selectedIndex == index) MaterialTheme.colorScheme.primary
//                                else Color.Transparent,
//                                RoundedCornerShape(4.dp)
//                            ),
//                        contentAlignment = Center
//                    ) {
//
//                        Icon(
//                            painterResource(icon.icon),
//                            null,
//                            Modifier.size(24.dp),
//                            tint = if (selectedIndex == index) Color.White else Color.Gray
//                        )
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//
//}