package com.example.expensetracker.presentation.screens.budgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
@Composable
@Preview
fun BudgetPreview() {
    BudgetScreen(
        navController = rememberNavController()
    )
}