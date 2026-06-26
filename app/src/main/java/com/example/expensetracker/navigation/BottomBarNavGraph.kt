package com.example.expensetracker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.R
import com.example.expensetracker.presentation.screens.budgets.BudgetScreen
import com.example.expensetracker.presentation.screens.dashboard.DashboardScreen
import com.example.expensetracker.presentation.screens.expenses.ExpensesScreen
import com.example.expensetracker.presentation.screens.profile.ProfileScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val dashboardRoute = stringResource(id = R.string.route_dashboard)
    val expensesRoute = stringResource(id = R.string.route_expenses)
    val budgetRoute = stringResource(id = R.string.route_budget)
    val profileRoute = stringResource(id = R.string.route_profile)

    NavHost(
        navController = navController,
        startDestination = dashboardRoute,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(dashboardRoute) {
            DashboardScreen(
                navController = navController
            )
        }

        composable(expensesRoute) {
            ExpensesScreen(
                navController = navController
            )
        }

        composable(budgetRoute) {
            BudgetScreen(navController = navController)
        }

        composable(profileRoute) {
            ProfileScreen(navController = navController)
        }
    }
}