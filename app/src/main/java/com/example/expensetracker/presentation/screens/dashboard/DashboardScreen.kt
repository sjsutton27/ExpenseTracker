package com.example.expensetracker.presentation.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.expensetracker.presentation.components.AppHeader

@Composable
fun DashboardScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AppHeader(
            title = "Dashboard",
            showBackButton = false,
            navController = navController
        )
    }
}