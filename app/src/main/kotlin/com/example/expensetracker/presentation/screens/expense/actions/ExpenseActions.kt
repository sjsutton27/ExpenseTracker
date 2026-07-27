package com.example.expensetracker.presentation.screens.expense.actions

import com.example.expensetracker.data.model.expense.ExpenseItem

data class ExpenseActions(
    val onAddExpenseClick: () -> Unit,
    val onCancelAdd: () -> Unit,
    val onSaveNewExpense: (ExpenseItem) -> Unit,
    val onEditClick: (String) -> Unit,
    val onCancelEdit: () -> Unit,
    val onSaveEdit: (ExpenseItem) -> Unit,
    val onDeleteClick: (String) -> Unit
)
