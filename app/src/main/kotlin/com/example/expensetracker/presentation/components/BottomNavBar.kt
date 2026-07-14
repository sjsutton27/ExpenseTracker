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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.expensetracker.R
import com.example.expensetracker.data.model.BottomNavItem

@Composable
fun BottomNavBar(
    navController: NavHostController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        BottomNavItem(
            route = stringResource(id = R.string.route_dashboard),
            title = stringResource(id = R.string.label_dashboard),
            icon = Icons.Default.AccountBalance
        ), BottomNavItem(
            route = stringResource(id = R.string.route_expenses),
            title = stringResource(id = R.string.label_expenses),
            icon = Icons.Default.Receipt
        ), BottomNavItem(
            route = stringResource(id = R.string.route_income),
            title = stringResource(id = R.string.label_income),
            icon = Icons.Default.AccountBalanceWallet
        ),BottomNavItem(
            route = stringResource(id = R.string.route_profile),
            title = stringResource(id = R.string.label_profile),
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