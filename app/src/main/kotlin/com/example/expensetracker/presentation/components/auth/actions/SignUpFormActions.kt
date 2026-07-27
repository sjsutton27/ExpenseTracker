package com.example.expensetracker.presentation.components.auth.actions

data class SignUpFormActions(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onConfirmPasswordChange: (String) -> Unit,
    val onSignUpClick: () -> Unit
)