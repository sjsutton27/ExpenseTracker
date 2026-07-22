package com.example.expensetracker.domain.use_case

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.AuthRepository
import com.example.expensetracker.domain.util.ValidEmail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String, confirmPassword: String): Flow<Resource<String>> {
        val errorMessage = when {
            email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> "Fields cannot be empty"
            !ValidEmail.isValid(email) -> "Invalid email format"
            password != confirmPassword -> "Passwords do not match"
            !validatePassword(password) -> "Password must contain at least one uppercase letter, one number, and one special character"
            else -> null
        }

        if (errorMessage != null) {
            return flow {
                emit(value = Resource.Error(errorMessage))
            }
        }

        return repository.signUp(email, password)
    }

    private fun validatePassword(password: String): Boolean {
        val hasUpperCase = password.any { char -> char.isUpperCase() }
        val hasDigit = password.any { char -> char.isDigit() }
        val hasSpecialChar = password.any { char -> !char.isLetterOrDigit() }
        return hasUpperCase && hasDigit && hasSpecialChar
    }
}
