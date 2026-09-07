package com.example.expensetracker.domain.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.income.IncomeItem
import kotlinx.coroutines.flow.Flow

interface IncomeRepository {
    fun addIncome(income: IncomeItem): Flow<Resource<IncomeItem>>
    fun updateIncome(income: IncomeItem): Flow<Resource<IncomeItem>>
    fun getIncome(): Flow<Resource<List<IncomeItem>>>
    fun deleteIncome(id: String): Flow<Resource<Unit>>
}