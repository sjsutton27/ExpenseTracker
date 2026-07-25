package com.example.expensetracker.di

import com.example.expensetracker.domain.use_case.auth.ForgotPasswordUseCase
import com.example.expensetracker.domain.use_case.auth.LoginUseCase
import com.example.expensetracker.domain.use_case.auth.SignUpUseCase
import com.example.expensetracker.domain.use_case.expense.*
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        LoginUseCase(
            repository = get()
        )
    }
    factory {
        SignUpUseCase(
            repository = get()
        )
    }
    factory {
        ForgotPasswordUseCase(
            repository = get()
        )
    }
    factory {
        AddExpenseUseCase(
            repository = get()
        )
    }
    factory {
        UpdateExpenseUseCase(
            repository = get()
        )
    }
    factory {
        DeleteExpenseUseCase(
            repository = get()
        )
    }
    factory {
        GetExpensesUseCase(
            repository = get()
        )
    }
    factory {
        ExpenseUseCases(
            addExpenseUseCase = get(),
            updateExpenseUseCase = get(),
            deleteExpenseUseCase = get(),
            getExpensesUseCase = get()
        )
    }
}