package com.example.expensetracker.domain.use_case

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String): Flow<Resource<String>> {
        val errorMessage = when {
            email.isBlank() || password.isBlank() -> "Email and password cannot be empty"
            !validateEmail(email) -> "Invalid email format"
            else -> null
        }

        if (errorMessage != null) {
            return flow {
                emit(value = Resource.Error(errorMessage))
            }
        }
        return repository.login(email, password)
    }

    private fun validateEmail(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")
        return email.matches(emailRegex)
    }
}
