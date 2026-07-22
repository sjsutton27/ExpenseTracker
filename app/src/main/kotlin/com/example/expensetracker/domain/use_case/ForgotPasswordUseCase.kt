package com.example.expensetracker.domain.use_case

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.expensetracker.domain.util.ValidEmail

class ForgotPasswordUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Resource<String>> {
        val errorMessage = when {
            email.isBlank() -> "Email cannot be empty"
            !ValidEmail.isValid(email) -> "Invalid email format"
            else -> null
        }

        if (errorMessage != null) {
            return flow {
                emit(value = Resource.Error(errorMessage))
            }
        }
        return repository.forgotPassword(email)
    }
}
