package com.example.expensetracker.data.remote.api

import com.example.expensetracker.data.remote.responses.ExpenseImage
import retrofit2.http.GET
import retrofit2.http.Query

interface LogoApi {
    @GET("search")
    suspend fun searchLogos(
        @Query("q") merchant: String
    ): ExpenseImage
}
