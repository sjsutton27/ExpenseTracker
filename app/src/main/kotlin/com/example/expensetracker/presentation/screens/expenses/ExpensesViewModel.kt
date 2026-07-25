package com.example.expensetracker.presentation.screens.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.domain.use_case.expense.ExpenseUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val expenseUseCases: ExpenseUseCases = ExpenseUseCases()
): ViewModel() {


    private val _expenses =
        MutableStateFlow<List<ExpenseItem>>(emptyList())
    val expenses =
        _expenses.asStateFlow()
    private val _getExpensesState =
        MutableStateFlow<Resource<List<ExpenseItem>>?>(null)
    val getExpensesState =
        _getExpensesState.asStateFlow()
    private val _expenseState =
        MutableStateFlow<Resource<ExpenseItem>?>(null)
    val expenseState =
        _expenseState.asStateFlow()
    private val _deleteExpenseState =
        MutableStateFlow<Resource<Unit>?>(null)
    val deleteExpenseState =
        _deleteExpenseState.asStateFlow()

    init {
        getExpenses()
    }

    private fun getExpenses() {
        viewModelScope.launch {
            expenseUseCases.getExpensesUseCase()
                .collect { result ->
                    _getExpensesState.value = result
                    if (result is Resource.Success<*>) {
                        _expenses.value = result.data ?: emptyList()
                    }
                }
        }
    }

    fun addExpense(expense: ExpenseItem) {
        viewModelScope.launch {
            expenseUseCases.addExpenseUseCase(expense)
                .collect { result ->
                    _expenseState.value = result
                }
        }
    }

    fun updateExpense(expense: ExpenseItem) {
        viewModelScope.launch {
            expenseUseCases.updateExpenseUseCase(expense)
                .collect { result ->
                    _expenseState.value = result
                }
        }
    }

    fun deleteExpense(id: String) {
        viewModelScope.launch {
            expenseUseCases.deleteExpenseUseCase(id)
                .collect { result ->
                    _deleteExpenseState.value = result
                }
        }
    }

    fun resetExpenseState() {
        _expenseState.value = null
    }

    fun resetGetExpensesState() {
        _getExpensesState.value = null
    }

    fun resetDeleteExpenseState() {
        _deleteExpenseState.value = null
    }
}