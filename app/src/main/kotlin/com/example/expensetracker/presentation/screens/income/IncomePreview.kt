package com.example.expensetracker.presentation.screens.income

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
@Composable
@Preview
fun IncomePreview() {
    IncomeScreen(
        navController = rememberNavController()
    )
}