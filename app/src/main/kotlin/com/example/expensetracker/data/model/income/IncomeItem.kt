package com.example.expensetracker.data.model.income

import com.example.expensetracker.data.model.Frequency

data class IncomeItem(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val category: IncomeCategory = IncomeCategory.OTHER,
    val frequency: Frequency = Frequency.NONE,
    val date: Long = System.currentTimeMillis()
)
