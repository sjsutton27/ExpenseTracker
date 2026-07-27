package com.example.expensetracker.presentation.screens.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.use_case.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<String>?>(value = null)
    val loginState: StateFlow<Resource<String>?> = _loginState

    fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            _loginState.value = result
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _loginState.value = null
    }
}