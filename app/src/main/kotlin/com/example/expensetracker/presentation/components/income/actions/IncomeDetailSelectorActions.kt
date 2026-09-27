package com.example.expensetracker.presentation.components.income.actions

import com.example.expensetracker.data.model.Frequency
import com.example.expensetracker.data.model.income.IncomeCategory

data class IncomeDetailSelectorActions(
    val onDateChange: (Long) -> Unit = {},
    val onCategoryChange: (IncomeCategory) -> Unit = {},
    val onFrequencyChange: (Frequency) -> Unit = {}
)