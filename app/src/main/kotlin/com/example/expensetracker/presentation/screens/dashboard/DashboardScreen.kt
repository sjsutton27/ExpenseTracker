package com.example.expensetracker.presentation.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.expensetracker.R
import com.example.expensetracker.presentation.components.AppHeader
import com.example.expensetracker.presentation.components.TimeFrameTab

@Composable
fun DashboardScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(R.string.label_dashboard),
                showBackButton = false,
                navController = navController
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {

            TimeFrameTab()

            // TODO: Dashboard cards
            // Total Income
            // Total Expense
            // Remaining Balance
            // Recent Transactions
        }
    }
}