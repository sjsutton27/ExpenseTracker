package com.example.expensetracker.presentation.screens.income

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.expensetracker.R
import com.example.expensetracker.presentation.components.AppHeader

@Composable
fun IncomeScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AppHeader(
            title = stringResource(id = R.string.label_income),
            showBackButton = true,
            navController = navController
        )
    }
}