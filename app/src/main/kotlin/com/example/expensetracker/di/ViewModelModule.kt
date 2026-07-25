package com.example.expensetracker.di

import com.example.expensetracker.presentation.screens.authentication.login.LoginViewModel
import com.example.expensetracker.presentation.screens.authentication.reset_password.ForgotPasswordViewModel
import com.example.expensetracker.presentation.screens.authentication.sign_up.SignUpViewModel
import com.example.expensetracker.presentation.screens.expenses.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ExpensesViewModel(
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
