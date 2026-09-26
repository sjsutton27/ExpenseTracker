package com.example.expensetracker.domain.use_case.income

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow

class DeleteIncomeUseCase(
    private val repository: IncomeRepository
) {
    operator fun invoke(id: String): Flow<Resource<Unit>> {
        return repository.deleteIncome(id = id)
    }
}
