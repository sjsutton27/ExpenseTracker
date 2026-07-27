package com.example.expensetracker.presentation.screens.expense.actions

data class ExpenseEffectActions(
    val onResetGetExpensesState: () -> Unit,
    val onResetExpenseState: () -> Unit,
    val onResetDeleteExpenseState: () -> Unit,
    val onEditComplete: () -> Unit,
    val onAddComplete: () -> Unit
)
