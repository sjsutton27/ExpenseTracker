package com.example.expensetracker.di

import org.koin.core.module.Module

val appModule: List<Module> = listOf(
    firebaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)
