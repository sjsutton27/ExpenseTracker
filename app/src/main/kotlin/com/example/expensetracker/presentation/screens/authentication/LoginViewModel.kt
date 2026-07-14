package com.example.expensetracker.presentation.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * TODO: Need to make sure when the app is exited need to logout the user
 * TODO: Inactivity to log out the user
 */
class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableStateFlow<Resource<String>?>(null)
    val loginState: StateFlow<Resource<String>?> = _loginState

    fun login(email: String, password: String) {
        val errorMessage = when {
            email.isBlank() || password.isBlank() -> "Email and password cannot be empty"
            else -> null
        }

        if (errorMessage != null) {
            _loginState.value = Resource.Error(errorMessage)
            return
        }

        _loginState.value = Resource.Loading()
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginState.value = Resource.Success("Login Successful")
                    } else {
                        val exception = task.exception
                        val loginErrorMessage = when (exception) {
                            is FirebaseAuthInvalidUserException -> "Invalid Email. Please Sign Up or try again."
                            is FirebaseAuthInvalidCredentialsException -> "Incorrect password. Please try again."
                            else -> exception?.message ?: "Login Failed"
                        }
                        _loginState.value = Resource.Error(loginErrorMessage)
                    }
                }
        }
    }

    fun resetState() {
        _loginState.value = null
    }
}
