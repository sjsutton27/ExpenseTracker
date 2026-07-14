package com.example.expensetracker.presentation.screens.profile

import androidx.compose.foundation.layout.Box
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

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(id = R.string.label_profile),
                showBackButton = true,
                navController = navController
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            // TODO: Settings
            // Account
            // History
            // Dark Mode
            // Change password
            // Delete Account
        }
    }
}