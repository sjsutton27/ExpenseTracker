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
import com.example.expensetracker.presentation.screens.login.LoginScreen
import com.example.expensetracker.presentation.screens.login.SignUpScreen
import com.example.expensetracker.presentation.screens.profile.ProfileScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ExpenseNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val loginRoute = stringResource(id = R.string.route_login)
    val signupRoute = stringResource(id = R.string.route_signup)
    val dashboardRoute = stringResource(id = R.string.route_dashboard)
    val expensesRoute = stringResource(id = R.string.route_expenses)
    val budgetRoute = stringResource(id = R.string.route_budget)
    val profileRoute = stringResource(id = R.string.route_profile)

    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDest = if (currentUser != null) dashboardRoute else loginRoute

    NavHost(
        navController = navController,
        startDestination = startDest,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = loginRoute) {
            LoginScreen(navController)
        }
        composable(route = signupRoute) {
            SignUpScreen(navController = navController)
        }
        composable(route = dashboardRoute) {
            DashboardScreen(navController = navController)
        }
        composable(route = expensesRoute) {
            ExpensesScreen(navController = navController)
        }
        composable(route = budgetRoute) {
            BudgetScreen(navController = navController)
        }
        composable(route = profileRoute) {
            ProfileScreen(navController = navController)
        }
    }
}