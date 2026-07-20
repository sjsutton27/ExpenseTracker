package com.example.expensetracker.data.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepository {

    override fun signUp(email: String, password: String): Flow<Resource<String>> = flow {
        emit(value = Resource.Loading())
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(value = Resource.Success(data = "Sign Up Successful"))
        } catch (e: Exception) {
            emit(value = Resource.Error(message = e.message ?: "Sign Up Failed"))
        }
    }

    override fun login(email: String, password: String): Flow<Resource<String>> = flow {
        emit(value = Resource.Loading())
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(value = Resource.Success(data = "Login Successful"))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthInvalidUserException -> "Invalid Email. Please Sign Up or try again."
                is FirebaseAuthInvalidCredentialsException -> "Incorrect password. Please try again."
                else -> e.message ?: "Login Failed"
            }
            emit(value = Resource.Error(errorMessage))
        }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}
