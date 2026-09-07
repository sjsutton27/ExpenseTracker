package com.example.expensetracker.presentation.components.expense.actions

import com.example.expensetracker.data.model.Frequency
import com.example.expensetracker.data.model.expense.ExpenseCategory

data class DetailSelectorActions(
    val onDateChange: (Long) -> Unit,
    val onCategoryChange: (ExpenseCategory) -> Unit,
    val onFrequencyChange: (Frequency) -> Unit
)
