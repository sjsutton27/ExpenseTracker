package com.example.expensetracker.presentation.screens.expenses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.expensetracker.presentation.components.AppHeader

@Composable
fun ExpensesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        AppHeader(title = "Expenses", showBackButton = true)
    }
}