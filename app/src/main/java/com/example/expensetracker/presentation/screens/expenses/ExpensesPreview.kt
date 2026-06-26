package com.example.expensetracker.presentation.screens.expenses

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.presentation.screens.dashboard.DashboardScreen

@Composable
@Preview
fun ExpensePreview() {
    ExpensesScreen(
        navController = rememberNavController()
    )
}