package com.example.expensetracker.di

import com.example.expensetracker.data.repository.AuthRepositoryImpl
import com.example.expensetracker.data.repository.ExpenseRepositoryImpl
import com.example.expensetracker.domain.repository.AuthRepository
import com.example.expensetracker.domain.repository.ExpenseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(
            auth = get()
        )
    }
    single<ExpenseRepository> {
        ExpenseRepositoryImpl(
            auth = get(),
            database = get(),
            logoApi = get()
        )
    }
}