package com.example.expensetracker.presentation.screens.income.actions

data class IncomeEffectActions(
    val onResetGetIncomeState: () -> Unit,
    val onResetIncomeState: () -> Unit,
    val onResetDeleteIncomeState: () -> Unit,
    val onEditComplete: () -> Unit,
    val onAddComplete: () -> Unit
)
