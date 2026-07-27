package com.example.expensetracker.domain.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.data.remote.responses.ExpenseImage
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun addExpense(expense: ExpenseItem): Flow<Resource<ExpenseItem>>
    fun updateExpense(expense: ExpenseItem): Flow<Resource<ExpenseItem>>
    fun getExpenses(): Flow<Resource<List<ExpenseItem>>>
    fun deleteExpense(id: String): Flow<Resource<Unit>>
    suspend fun getMerchantLogo(
        merchant: String
    ): ExpenseImage?
}
