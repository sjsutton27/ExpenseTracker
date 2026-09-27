package com.example.expensetracker.presentation.components.income.actions

import com.example.expensetracker.data.model.income.IncomeItem

data class IncomeCardActions(
    val onEditClick: () -> Unit,
    val onCancelEdit: () -> Unit,
    val onSaveEdit: (IncomeItem) -> Unit,
    val onDeleteClick: () -> Unit
)