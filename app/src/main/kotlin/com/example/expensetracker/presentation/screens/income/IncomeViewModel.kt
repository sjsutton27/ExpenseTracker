package com.example.expensetracker.presentation.screens.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.domain.use_case.income.IncomeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IncomeViewModel(
    private val incomeUseCases: IncomeUseCases
) : ViewModel() {

    private val _income =
        MutableStateFlow<List<IncomeItem>>(value = emptyList())
    val income =
        _income.asStateFlow()

    private val _getIncomeState =
        MutableStateFlow<Resource<List<IncomeItem>>?>(null)
    val getIncomeState =
        _getIncomeState.asStateFlow()

    private val _incomeState =
        MutableStateFlow<Resource<IncomeItem>?>(null)
    val incomeState =
        _incomeState.asStateFlow()

    private val _deleteIncomeState =
        MutableStateFlow<Resource<Unit>?>(null)
    val deleteIncomeState =
        _deleteIncomeState.asStateFlow()

    init {
        getIncome()
    }

    private fun getIncome() {
        viewModelScope.launch {
            incomeUseCases.getIncomeUseCase()
                .collect { result ->
                    _getIncomeState.value = result

                    if (result is Resource.Success<*>) {
                        _income.value = result.data ?: emptyList()
                    }
                }
        }
    }

    fun addIncome(income: IncomeItem) {
        viewModelScope.launch {
            incomeUseCases.addIncomeUseCase(income)
                .collect { result ->
                    _incomeState.value = result
                }
        }
    }

    fun updateIncome(income: IncomeItem) {
        viewModelScope.launch {
            incomeUseCases.updateIncomeUseCase(income)
                .collect { result ->
                    _incomeState.value = result
                }
        }
    }

    fun deleteIncome(id: String) {
        viewModelScope.launch {
            incomeUseCases.deleteIncomeUseCase(id)
                .collect { result ->
                    _deleteIncomeState.value = result
                }
        }
    }

    fun resetIncomeState() {
        _incomeState.value = null
    }

    fun resetGetIncomeState() {
        _getIncomeState.value = null
    }

    fun resetDeleteIncomeState() {
        _deleteIncomeState.value = null
    }
}
