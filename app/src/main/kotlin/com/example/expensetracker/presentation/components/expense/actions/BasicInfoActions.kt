package com.example.expensetracker.presentation.components.expense.actions

data class BasicInfoActions(
    val onTitleChange: (String) -> Unit,
    val onAmountChange: (String) -> Unit,
    val onMerchantChange: (String) -> Unit
)
