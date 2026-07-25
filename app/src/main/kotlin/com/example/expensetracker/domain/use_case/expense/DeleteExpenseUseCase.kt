package com.example.expensetracker.domain.use_case.expense

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.data.repository.ExpenseRepositoryImpl
import com.example.expensetracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class DeleteExpenseUseCase(
    private val repository: ExpenseRepository = ExpenseRepositoryImpl()
){
    operator fun invoke(id: String): Flow<Resource<Unit>> {
        return repository.deleteExpense(id = id)
    }
}