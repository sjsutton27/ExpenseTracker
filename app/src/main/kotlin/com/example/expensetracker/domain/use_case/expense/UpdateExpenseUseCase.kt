package com.example.expensetracker.domain.use_case.expense

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.data.repository.ExpenseRepositoryImpl
import com.example.expensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class UpdateExpenseUseCase(
    private val repository: ExpenseRepository = ExpenseRepositoryImpl()
){
    operator fun invoke(expense: ExpenseItem): Flow<Resource<ExpenseItem>> {
        return repository.updateExpense(expense)
    }
}