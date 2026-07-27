package com.example.expensetracker.data.model.expense
import com.example.expensetracker.common.Resource

data class ExpenseScreenState(
    val expenses: List<ExpenseItem>,
    val getExpensesState: Resource<List<ExpenseItem>>?,
    val addingExpense: Boolean,
    val editingExpenseId: String?
)
