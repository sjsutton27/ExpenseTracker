package com.example.expensetracker.data.model

data class ExpenseItem(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val date: String = "",
    val category: ExpenseCategory = ExpenseCategory.OTHER,
    val merchant: String = "",
    val isFrequent: Boolean = false,
    val frequency : ExpenseFrequency = ExpenseFrequency.WEEKLY,
    val imageUrl: String = ""
)