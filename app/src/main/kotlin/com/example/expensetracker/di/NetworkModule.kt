package com.example.expensetracker.di

import com.example.expensetracker.common.Constants.IMAGE_BASE_URL
import com.example.expensetracker.data.remote.api.LogoApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
    single<LogoApi> {
        get<Retrofit>()
            .create(LogoApi::class.java)
    }
}