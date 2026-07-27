package com.example.expensetracker.data.model.expense

import com.example.expensetracker.common.currentDate

data class ExpenseItem(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.00,
    val date: Long = currentDate(),
    val category: ExpenseCategory = ExpenseCategory.OTHER,
    val merchant: String = "",
    val merchantDomain: String = "",
    val frequency: ExpenseFrequency = ExpenseFrequency.NONE,
    val imageUrl: String = "",
)
