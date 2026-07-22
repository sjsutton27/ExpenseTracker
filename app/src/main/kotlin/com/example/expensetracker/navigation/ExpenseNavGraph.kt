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
import com.example.expensetracker.presentation.screens.authentication.reset_password.ForgotPasswordScreen
import com.example.expensetracker.presentation.screens.income.IncomeScreen
import com.example.expensetracker.presentation.screens.dashboard.DashboardScreen
import com.example.expensetracker.presentation.screens.expenses.ExpensesScreen
import com.example.expensetracker.presentation.screens.authentication.login.LoginScreen
import com.example.expensetracker.presentation.screens.authentication.sign_up.SignUpScreen
import com.example.expensetracker.presentation.screens.profile.ProfileScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ExpenseNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val loginRoute = stringResource(id = R.string.route_login)
    val signupRoute = stringResource(id = R.string.route_signup)
    val forgotPasswordRoute = stringResource(id = R.string.route_forgot_password)
    val dashboardRoute = stringResource(id = R.string.route_dashboard)
    val expensesRoute = stringResource(id = R.string.route_expenses)
    val incomeRoute = stringResource(id = R.string.route_income)
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
        composable(route = forgotPasswordRoute) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(route = dashboardRoute) {
            DashboardScreen(navController = navController)
        }
        composable(route = expensesRoute) {
            ExpensesScreen(navController = navController)
        }
        composable(route = incomeRoute) {
            IncomeScreen(navController = navController)
        }
        composable(route = profileRoute) {
            ProfileScreen(navController = navController)
        }
    }
}