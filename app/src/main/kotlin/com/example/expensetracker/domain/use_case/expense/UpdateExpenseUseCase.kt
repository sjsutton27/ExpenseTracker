package com.example.expensetracker.domain.use_case.expense

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.expense.ExpenseItem
import com.example.expensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class UpdateExpenseUseCase(
    private val repository: ExpenseRepository
) {
    operator fun invoke(expense: ExpenseItem): Flow<Resource<ExpenseItem>> {
        return repository.updateExpense(expense)
    }
}
