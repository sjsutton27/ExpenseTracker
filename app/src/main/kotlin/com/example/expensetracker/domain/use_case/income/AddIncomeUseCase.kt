package com.example.expensetracker.domain.use_case.income

import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.domain.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow

class AddIncomeUseCase(
    private val repository: IncomeRepository
) {
    operator fun invoke(income: IncomeItem): Flow<Resource<IncomeItem>> {
        return repository.addIncome(income)
    }
}
