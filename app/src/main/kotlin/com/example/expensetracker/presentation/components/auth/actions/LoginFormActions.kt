package com.example.expensetracker.presentation.components.auth.actions

data class LoginFormActions(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onLoginClick: () -> Unit
)