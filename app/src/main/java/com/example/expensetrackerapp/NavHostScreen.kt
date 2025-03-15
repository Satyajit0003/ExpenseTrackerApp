package com.example.expensetrackerapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerapp.feature.add_expense.AddExpense
import com.example.expensetrackerapp.feature.home.HomeScreen
import com.example.expensetrackerapp.feature.stats.StatsScreen
import com.example.expensetrackerapp.ui.theme.Zinc

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBottomBar(
                navController = navController,
                items = listOf(
                    NavItem("home", R.drawable.ic_home),
                    NavItem("stats", R.drawable.ic_stats)
                )
            )
        }
    ) {
          NavHost(
              navController = navController,
              startDestination = "/home",
              modifier = Modifier.padding(it)
          ) {
              composable(route="/home") {
                  HomeScreen(navController)
               }

              composable(route="/addExpense") {
                  AddExpense(navController)
              }

              composable(route="/stats") {
                  StatsScreen(navController)
              }
          }
    }

}


data class NavItem(
    val route: String,
    val icon: Int
)

@Composable
fun NavigationBottomBar(
    navController: NavController,
    items: List<NavItem>
){
    val navStackBackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry.value?.destination?.route

    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Zinc,
                    selectedTextColor = Zinc,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )

            )
        }
    }
}