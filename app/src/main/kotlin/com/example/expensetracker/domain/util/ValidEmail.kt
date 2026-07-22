package com.example.expensetracker.domain.util

object ValidEmail {
    private val emailRegex =
        Regex(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun isValid(email: String): Boolean {
        return email.matches(
            regex = emailRegex
        )
    }
}