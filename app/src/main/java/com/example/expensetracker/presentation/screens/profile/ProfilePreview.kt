package com.example.expensetracker.presentation.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun ProfilePreview() {
    ProfileScreen(
        navController = rememberNavController()
    )
}