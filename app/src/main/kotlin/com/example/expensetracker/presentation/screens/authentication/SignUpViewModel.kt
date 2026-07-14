package com.example.expensetracker.presentation.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _signUpState = MutableStateFlow<Resource<String>?>(null)
    val signUpState: StateFlow<Resource<String>?> = _signUpState

    fun signUp(email: String, password: String, confirmPassword: String) {
        val errorMessage = when {
            blankFields(email, password, confirmPassword) -> "Fields cannot be empty"
            !validateEmail(email) -> "Invalid email format"
            passwordsDoNotMatch(password, confirmPassword) -> "Passwords do not match"
            !validatePassword(password) -> "Password must contain at least one uppercase letter, one number, and one special character"
            else -> null
        }

        if (errorMessage != null) {
            _signUpState.value = Resource.Error(errorMessage)
            return
        }

        _signUpState.value = Resource.Loading()
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _signUpState.value = Resource.Success("Sign Up Successful")
                    } else {
                        _signUpState.value = Resource.Error(task.exception?.message ?: "Sign Up Failed")
                    }
                }
        }
    }
    
    private fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")
        return email.matches(emailRegex)
    }
    
    private fun blankFields(email: String, password: String, confirmPassword: String): Boolean {
        return email.isBlank() || password.isBlank() || confirmPassword.isBlank()
    }
    
    private fun passwordsDoNotMatch(password: String, confirmPassword: String): Boolean {
        return password != confirmPassword
    }

    private fun validatePassword(password: String): Boolean {
        val hasUpperCase = password.any { char -> char.isUpperCase() }
        val hasDigit = password.any { char -> char.isDigit() }
        val hasSpecialChar = password.any { char -> !char.isLetterOrDigit() }
        return hasUpperCase && hasDigit && hasSpecialChar
    }

    fun resetState() {
        _signUpState.value = null
    }
}
