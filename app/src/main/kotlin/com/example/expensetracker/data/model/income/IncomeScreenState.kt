package com.example.expensetracker.data.model.income

import com.example.expensetracker.common.Resource

data class IncomeScreenState(
    val incomes: List<IncomeItem>,
    val getIncomesState: Resource<List<IncomeItem>>?,
    val addingIncome: Boolean,
    val editingIncomeId: String?
)
