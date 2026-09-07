package com.example.expensetracker.data.model.expense

import com.example.expensetracker.common.currentDate
import com.example.expensetracker.data.model.Frequency

data class ExpenseItem(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.00,
    val date: Long = currentDate(),
    val category: ExpenseCategory = ExpenseCategory.OTHER,
    val merchant: String = "",
    val merchantDomain: String = "",
    val frequency: Frequency = Frequency.NONE,
    val imageUrl: String = "",
)
