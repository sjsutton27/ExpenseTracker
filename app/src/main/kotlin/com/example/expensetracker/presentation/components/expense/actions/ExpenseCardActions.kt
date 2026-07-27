package com.example.expensetracker.presentation.components.expense.actions

import com.example.expensetracker.data.model.expense.ExpenseItem

data class ExpenseCardActions(
    val onEditClick: () -> Unit,
    val onCancelEdit: () -> Unit,
    val onSaveEdit: (ExpenseItem) -> Unit,
    val onDeleteClick: () -> Unit
)