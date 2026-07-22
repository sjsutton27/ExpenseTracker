package com.example.expensetracker.domain.repository

import com.example.expensetracker.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String, password: String): Flow<Resource<String>>
    fun login(email: String, password: String): Flow<Resource<String>>
    fun forgotPassword(email: String): Flow<Resource<String>>
    fun logout()
    fun isUserLoggedIn(): Boolean
}
