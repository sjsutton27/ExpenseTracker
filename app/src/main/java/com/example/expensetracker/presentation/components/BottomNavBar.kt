package com.example.expensetracker.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.expensetracker.data.model.BottomNavItem

@Composable
fun BottomNavBar(
    navController: NavHostController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        BottomNavItem(
            route = "dashboard",
            title = "Dashboard",
            icon = Icons.Default.AccountBalance
        ), BottomNavItem(
            route = "expenses",
            title = "Expenses",
            icon = Icons.Default.Receipt
        ), BottomNavItem(
            route = "budget",
            title = "Budget",
            icon = Icons.Default.AccountBalanceWallet
        ),BottomNavItem(
            route = "profile",
            title = "Profile",
            icon = Icons.Default.Person
        )
    )

    NavigationBar {
        navItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(navItem.title)
                }
            )
        }
    }
}