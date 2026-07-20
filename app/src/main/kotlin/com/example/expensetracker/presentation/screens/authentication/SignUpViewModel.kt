package com.example.expensetracker.presentation.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.repository.AuthRepositoryImpl
import com.example.expensetracker.domain.use_case.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase = SignUpUseCase(repository = AuthRepositoryImpl())
) : ViewModel() {

    private val _signUpState = MutableStateFlow<Resource<String>?>(value = null)
    val signUpState: StateFlow<Resource<String>?> = _signUpState

    fun signUp(email: String, password: String, confirmPassword: String) {
        signUpUseCase(email, password, confirmPassword).onEach { result ->
            _signUpState.value = result
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _signUpState.value = null
    }
}
