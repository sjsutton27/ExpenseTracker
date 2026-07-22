package com.example.expensetracker.data.repository

import com.example.expensetracker.common.Resource
import com.example.expensetracker.domain.repository.AuthRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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
            emit(
                value = Resource.Success(
                    data = "Sign Up Successful"
                )
            )
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthInvalidCredentialsException ->
                    "Invalid email format."
                is FirebaseAuthUserCollisionException ->
                    "An account already exists with this email."
                is FirebaseNetworkException ->
                    "No internet connection. Please try again."
                else ->
                    e.message ?: "Sign Up Failed"
            }
            emit(value = Resource.Error(errorMessage))
        }
    }

    override fun login(email: String, password: String): Flow<Resource<String>> = flow {
        emit(value = Resource.Loading())
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(
                value = Resource.Success(
                    data = "Login Successful"
                )
            )
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthInvalidUserException ->
                    "No account found with this email. Please sign up."
                is FirebaseAuthInvalidCredentialsException ->
                    "Incorrect email or password. Please try again."
                is FirebaseNetworkException ->
                    "No internet connection. Please try again."
                else ->
                    e.message ?: "Login Failed"
            }
            emit(value = Resource.Error(errorMessage))
        }
    }
    
    override fun forgotPassword(email: String): Flow<Resource<String>> = flow {
        emit(value = Resource.Loading())
        try {
            auth.sendPasswordResetEmail(email).await()
            emit(value = Resource.Success(data = "Password Reset Email Sent"))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is FirebaseAuthInvalidCredentialsException ->
                    "Please enter a valid email address."
                is FirebaseNetworkException ->
                    "No internet connection. Please try again."
                is FirebaseTooManyRequestsException ->
                    "Too many requests. Please try again later."
                else ->
                    e.message ?: "Password reset failed."
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
