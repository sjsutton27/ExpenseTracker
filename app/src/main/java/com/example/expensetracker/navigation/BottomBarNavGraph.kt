package com.example.expensetracker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.presentation.screens.budgets.BudgetScreen
import com.example.expensetracker.presentation.screens.dashboard.DashboardScreen
import com.example.expensetracker.presentation.screens.expenses.ExpensesScreen
import com.example.expensetracker.presentation.screens.profile.ProfileScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("dashboard") {
            DashboardScreen(
                navController = navController
            )
        }

        composable("expenses") {
            ExpensesScreen(
                navController = navController
            )
        }

        composable("budget") {
            BudgetScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }
    }
}