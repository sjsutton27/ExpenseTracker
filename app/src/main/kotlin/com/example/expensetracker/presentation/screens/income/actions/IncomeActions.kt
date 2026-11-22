package com.example.expensetracker.presentation.screens.income.actions

import com.example.expensetracker.data.model.income.IncomeItem

data class IncomeActions(
    val onAddIncomeClick: () -> Unit,
    val onCancelAdd: () -> Unit,
    val onSaveNewIncome: (IncomeItem) -> Unit,
    val onEditClick: (String) -> Unit,
    val onCancelEdit: () -> Unit,
    val onSaveEdit: (IncomeItem) -> Unit,
    val onDeleteClick: (String) -> Unit
)
