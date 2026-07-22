package com.example.expensetracker.presentation.screens.authentication.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.repository.AuthRepositoryImpl
import com.example.expensetracker.domain.use_case.ForgotPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ForgotPasswordViewModel(
    private val forgotPasswordUseCase: ForgotPasswordUseCase = ForgotPasswordUseCase(repository = AuthRepositoryImpl())
): ViewModel() {
    private val _forgotPasswordState = MutableStateFlow<Resource<String>?>(value = null)
    val forgotPasswordState: StateFlow<Resource<String>?> = _forgotPasswordState

    fun forgotPassword(email: String) {
        forgotPasswordUseCase(email).onEach { result ->
            _forgotPasswordState.value = result
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _forgotPasswordState.value = null
    }
}