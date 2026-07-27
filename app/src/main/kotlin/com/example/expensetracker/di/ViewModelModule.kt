package com.example.expensetracker.di

import com.example.expensetracker.presentation.screens.auth.login.LoginViewModel
import com.example.expensetracker.presentation.screens.auth.reset_password.ForgotPasswordViewModel
import com.example.expensetracker.presentation.screens.auth.sign_up.SignUpViewModel
import com.example.expensetracker.presentation.screens.expense.ExpenseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ExpenseViewModel(
            expenseUseCases = get()
        )
    }
    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }
    viewModel {
        SignUpViewModel(
            signUpUseCase = get()
        )
    }
    viewModel {
        ForgotPasswordViewModel(
            forgotPasswordUseCase = get()
        )
    }
}
